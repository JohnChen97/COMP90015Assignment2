import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main function to start the Client, with the server address and port number.
 * @param args
 */
public class TempPanel{

    private String keyWord;

    private JPanel panel;
    private JLabel meaningLabel;


    public TempPanel(){
        this.panel = new JPanel(new FlowLayout());
        this.meaningLabel = new JLabel("Meanings");
        panel.add(meaningLabel);

    }
    public JPanel getPanel(){
        return panel;
    }


    public void setMeaningLabelContent(String newContent) {
        this.meaningLabel.setText(newContent);
    }

}