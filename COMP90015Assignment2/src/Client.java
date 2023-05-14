import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {

    public static void main(String[] args){

        try {
//            RemoteWhiteBoard remoteWhiteBoard = (RemoteWhiteBoard) Naming.lookup("rmi://" + this.host + ":" + this.port + "/WhiteBoard");
            System.setProperty("java.security.policy", "RemoteWhiteBoardPolicy");
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
            IRemoteWhiteBoard stub = (IRemoteWhiteBoard) registry.lookup("RemoteWhiteBoardServer");
            stub.displayFrame();

        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}




