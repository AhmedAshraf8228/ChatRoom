package iti.jets.chatroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
        
    }

    @Override
    public void start( Stage primaryStage){
        primaryStage.setTitle("Chat Application");
        Image icon = new Image(String.valueOf(getClass().getResource("/iti/jets/chatroom/chat.png")));
        primaryStage.getIcons().add(icon);

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/iti/jets/chatroom/firstScene.fxml"));
        Parent root = null;
        try  {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Could not load the test.fxml file.");
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
