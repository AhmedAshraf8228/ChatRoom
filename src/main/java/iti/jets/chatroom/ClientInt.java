package iti.jets.chatroom;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInt extends Remote {
    void receive(String msg) throws RemoteException;
    void addClient(ArrayList<String> clients) throws RemoteException;

}
