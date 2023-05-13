import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class runWhiteBoardDirectly{

    public static void main(String[] args) {
        try {
            new RemoteWhiteBoard();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}