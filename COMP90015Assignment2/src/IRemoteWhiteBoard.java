import javax.swing.*;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteWhiteBoard extends Remote {

    public void clearImage() throws RemoteException;
    public void setColor(Color color) throws RemoteException;
    public void draw(int x, int y) throws RemoteException;

    public void erase(int x, int y) throws RemoteException;

    public void setFillSize(int fillSize) throws RemoteException;

    public void displayFrame() throws RemoteException;
}
