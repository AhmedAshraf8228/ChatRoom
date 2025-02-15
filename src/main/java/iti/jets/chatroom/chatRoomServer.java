package iti.jets.chatroom;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class chatRoomServer {
    public static void main(String[] args){
        try{
            ChatServerImpl obj = new ChatServerImpl();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("chatSer", obj);
            System.out.println("Server is running...");
        } catch(RemoteException ex){
            System.out.println(ex.getMessage());
        }
    }
}
