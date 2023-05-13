import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

public class WhiteBoardPanel extends JPanel implements IRemoteWhiteBoard {

    private Graphics2D graphics2D;
    private Image image;;
    private int currentX, currentY, oldX, oldY;

// https://www.youtube.com/watch?v=OOb1eil4PCo&t=435s
// Using code from the above whiteboard tutorial.

    public WhiteBoardPanel(){
        this.setSize(800, 600);
        setDoubleBuffered(false);
        // image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        // graphics2D = image.createGraphics();
        // graphics2D.setColor(Color.WHITE);
        // graphics2D.fillRect(0, 0, 800, 600);
        // graphics2D.setColor(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                draw(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                
                if (graphics2D != null){

                    currentX = e.getX();
                    currentY = e.getY();
                    graphics2D.drawLine(e.getX(), e.getY(), oldX, oldY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;

                }
            }
        });

       
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (this.image == null){
            this.image = createImage(800, 600);
            this.graphics2D = (Graphics2D) this.image.getGraphics();
            this.graphics2D.setColor(Color.WHITE);
            this.graphics2D.fillRect(0, 0, 800, 600);
            this.graphics2D.setColor(Color.BLACK);
            clearImage();
        }
    
        g.drawImage(this.image, 0, 0, this);
    }

    public void clearImage(){
        this.graphics2D.setPaint(Color.WHITE);
        this.graphics2D.fillRect(0, 0, 800, 600);
        this.graphics2D.setPaint(Color.BLACK);
        this.repaint();
    }


    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void draw(int x, int y) {
        if (graphics2D != null) {
            graphics2D.fillOval(x, y, 3, 3);
            this.repaint(x, y, 3, 3);
        }
    }

    @Override
    public void setColor(Color color) throws RemoteException {
        this.graphics2D.setPaint(color);
    }

    
}


