import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteWhiteBoardServer{
    public static void main(String[] args) {
        try{
            System.setProperty("java.security.policy", "RemoteWhiteBoardPolicy");
            RemoteWhiteBoard remoteWhiteBoard = new RemoteWhiteBoard();
            UnicastRemoteObject.unexportObject(remoteWhiteBoard, true);
            IRemoteWhiteBoard stub = (IRemoteWhiteBoard) UnicastRemoteObject.exportObject(remoteWhiteBoard, 0);

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
            registry.unbind("RemoteWhiteBoardServer");
            registry.bind("RemoteWhiteBoardServer", stub);
            System.out.println("RemoteWhiteBoardServer is ready.");
        }catch (NoSuchObjectException e) {
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
