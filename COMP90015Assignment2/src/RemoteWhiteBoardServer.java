import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteWhiteBoardServer {
    public static void main(String[] args) {
        try{
            RemoteWhiteBoard remoteWhiteBoard = new RemoteWhiteBoard();
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            registry.unbind("RemoteWhiteBoardServer");
            registry.bind("RemoteWhiteBoardServer", remoteWhiteBoard);
            System.out.println("RemoteWhiteBoardServer is ready.");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
