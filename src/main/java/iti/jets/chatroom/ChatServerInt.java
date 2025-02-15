package iti.jets.chatroom;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInt extends Remote {
    void tellOthers(String msg) throws RemoteException;

    void register(ClientInt clientRef , String name) throws
            RemoteException;

    void unRegister(ClientInt clientRef , String name) throws
            RemoteException;
}
