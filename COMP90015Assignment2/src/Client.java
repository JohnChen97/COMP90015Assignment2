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
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            IRemoteWhiteBoard remoteBoard = (IRemoteWhiteBoard) registry.lookup("RemoteWhiteBoardServer");
            remoteBoard.draw(0, 0);


        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

}




