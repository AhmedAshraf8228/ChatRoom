package iti.jets.chatroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientImpl extends UnicastRemoteObject implements ClientInt{
    HomeSceneController home ;
    protected ClientImpl() throws RemoteException {
    }
    protected ClientImpl(HomeSceneController home) throws RemoteException {
        this.home = home;
    }
    public void setHome(HomeSceneController home) {
        this.home = home;
    }

    @Override
    public void receive(String msg) throws RemoteException {
        home.addMess(msg);
    }

    @Override
    public void addClient(ArrayList<String> users) throws RemoteException {
        home.addUser(users);
    }
}
