module iti.jets.chatroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens iti.jets.chatroom to javafx.fxml;
    exports iti.jets.chatroom;
}