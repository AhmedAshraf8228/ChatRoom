package iti.jets.chatroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInt {
    Vector<ClientInt> clientsVector = new Vector<>();
    ArrayList<String> listNames = new ArrayList<>();

    public ChatServerImpl() throws RemoteException {
    }

    public void register(ClientInt clientRef, String name) {
        clientsVector.add(clientRef);
        listNames.add(name);
        for (ClientInt c : clientsVector) {
            try {

                c.addClient(listNames);
            } catch (RemoteException e) {
                System.out.println("error send new users");
            }
        }
        tellOthers("connect: " + name + "\n");
    }

    public void unRegister(ClientInt clientRef, String name) {
        clientsVector.remove(clientRef);
        listNames.remove(name);
        for (ClientInt c : clientsVector) {
            try {
                c.addClient(listNames);
            } catch (RemoteException e) {
                System.out.println("error send delete users");
            }
        }
        tellOthers("disconnect: " + name + "\n");
    }

    public void tellOthers(String msg) {
        System.out.println("Messge received: " + msg);
        for (ClientInt clientRef : clientsVector) {
            try {
                clientRef.receive(msg);
            } catch (RemoteException ex) {
                System.out.println("Can’t send msg to client!");
                System.out.println(ex.getMessage());
            }
        }
    }
}
