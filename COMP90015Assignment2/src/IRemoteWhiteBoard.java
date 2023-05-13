import javax.swing.*;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteWhiteBoard extends Remote {

    void clearImage() throws RemoteException;
    void setColor(Color color) throws RemoteException;
    void draw(int x, int y) throws RemoteException;
}
