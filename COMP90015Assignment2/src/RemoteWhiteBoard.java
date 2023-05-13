import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteWhiteBoard extends UnicastRemoteObject implements IRemoteWhiteBoard {

    private JFrame frame;
    private WhiteBoardPanel whiteBoardPanel;

    public RemoteWhiteBoard() throws RemoteException {
        this.frame = new JFrame("Whiteboard");
        whiteBoardPanel = new WhiteBoardPanel();
        this.frame.add(whiteBoardPanel);
        Button clearButton = new Button("Clear");
        Button redButton = new Button("Red");
        Button blueButton = new Button("Blue");
        Button greenButton = new Button("Green");
        Panel buttonPanel = new Panel();
        buttonPanel.add(clearButton);
        buttonPanel.add(redButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);
        this.frame.add(buttonPanel, BorderLayout.SOUTH);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        clearButton.addActionListener(e -> {
            try {
                whiteBoardPanel.clearImage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        redButton.addActionListener(e -> {
            try {
                whiteBoardPanel.setColor(Color.RED);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        blueButton.addActionListener(e -> {
            try {
                whiteBoardPanel.setColor(Color.BLUE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        greenButton.addActionListener(e -> {
            try {
                whiteBoardPanel.setColor(Color.GREEN);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


    }

    @Override
    public void setColor(Color color) throws RemoteException {
        this.whiteBoardPanel .setColor(color);
    }

    @Override
    public void draw(int x, int y) throws RemoteException {
        this.whiteBoardPanel .draw(x, y);
    }

    @Override
    public void clearImage() throws RemoteException {
        this.whiteBoardPanel .clearImage();
    }


    public static void main(String[] args) {
        try {
            new RemoteWhiteBoard();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
