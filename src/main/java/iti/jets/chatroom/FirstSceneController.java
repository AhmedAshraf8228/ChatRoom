package iti.jets.chatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class FirstSceneController {
    Registry reg ;
    ChatServerInt chatSer ;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label chatLaple;

    @FXML
    private TextField nameField;

    @FXML
    private Button goButton;

    @FXML
    private Button picButton;

    @FXML
    private ImageView mypic;

    @FXML
    void handleGoButton(ActionEvent event) {
        try {
            reg = LocateRegistry.getRegistry("127.0.0.1");
            chatSer = (ChatServerInt) reg.lookup("chatSer");
            System.out.println("Connection to server successful:....");
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
        }
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/iti/jets/chatroom/homeScene.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HomeSceneController controller = loader.getController();
            ClientImpl clientimp;
            try {
                 clientimp = new ClientImpl(controller);
                 //clientimp.setHome(controller);
                 chatSer.register(clientimp,nameField.getText());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            controller.setUserName(nameField.getText());
            controller.setpic(mypic.getImage());

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            controller.seters(chatSer,clientimp,stage,reg);
            stage.setScene(scene);

    }

    @FXML
    void handlePicButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            mypic.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
        }
    }

    @FXML
    void initialize() {
        assert chatLaple != null : "fx:id=\"chatLaple\" was not injected: check your FXML file 'firstScene.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'firstScene.fxml'.";
        assert goButton != null : "fx:id=\"goButton\" was not injected: check your FXML file 'firstScene.fxml'.";
        assert picButton != null : "fx:id=\"picButton\" was not injected: check your FXML file 'firstScene.fxml'.";
        assert mypic != null : "fx:id=\"mypic\" was not injected: check your FXML file 'firstScene.fxml'.";


    }

    public String gettext() {
        return nameField.getText();
    }
}
