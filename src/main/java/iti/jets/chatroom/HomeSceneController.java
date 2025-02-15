package iti.jets.chatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeSceneController {
    ChatServerInt chatSer ;
    ClientImpl clientImp;
    Stage stage;
    Registry reg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logoutButton;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextArea connectedTextArea;

    @FXML
    private ImageView imageV;

    @FXML
    private Label name;

    @FXML
    private Button sendButton;

    @FXML
    private TextField textF;

    @FXML
    void handleLogOutButton(ActionEvent event) {
        try {
            chatSer.unRegister(clientImp,name.getText());
            chatSer = null ;
            reg = null;
            System.out.println("success logout");
        } catch (RemoteException e) {
            System.out.println("can't unRegister:" + e.getMessage());
        }

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/iti/jets/chatroom/firstScene.fxml"));
        Parent root = null;
        try  {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Could not load the test.fxml file.");
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void handleSendButton(ActionEvent event) {
        try {
            chatSer.tellOthers(name.getText() + ": " + textF.getText()+"\n");
            textF.clear();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleTextField(ActionEvent event) {
        try {
            chatSer.tellOthers(name.getText() + ": " + textF.getText()+"\n");
            textF.clear();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert chatTextArea != null : "fx:id=\"chatTextArea\" was not injected: check your FXML file 'homeScene.fxml'.";
        assert connectedTextArea != null : "fx:id=\"connectedTextArea\" was not injected: check your FXML file 'homeScene.fxml'.";
        assert imageV != null : "fx:id=\"imageV\" was not injected: check your FXML file 'homeScene.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'homeScene.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'homeScene.fxml'.";
        assert textF != null : "fx:id=\"textF\" was not injected: check your FXML file 'homeScene.fxml'.";

    }

    public void setUserName(String name1) {
        name.setText(name1);
    }

    public void setpic(@SuppressWarnings("exports") Image image) {
        imageV.setImage(image);
    }

    public void seters(ChatServerInt chSer , ClientImpl client , Stage st, Registry registry){
        chatSer = chSer;
        clientImp = client;
        stage = st;
        reg = registry;
        stage.setOnCloseRequest(e -> {
            System.out.println("Closing the primary stage...");
            if (chatSer != null){
                try {
                    chatSer.unRegister(clientImp,name.getText());
                    chatSer = null;
                    reg=null;
                } catch (IOException  ioe) {
                    System.out.println("can't unRegister");
                }
            }
            System.exit(0);
        });
    }

    public void addMess(String mes){
        chatTextArea.appendText(mes);
    }

    public void addUser(ArrayList<String> users){
        connectedTextArea.clear();
        for (String s : users){
            connectedTextArea.appendText(s+"\n");
        }
    }


}
