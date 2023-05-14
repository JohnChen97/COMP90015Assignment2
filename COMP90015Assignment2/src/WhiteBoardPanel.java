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

    private int fillSize  = 3;
    private String shape = "circle";

// https://www.youtube.com/watch?v=OOb1eil4PCo&t=435s
// Using code from the above whiteboard tutorial.

    public WhiteBoardPanel(){
        this.setSize(800, 600);
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                //draw(e.getX(), e.getY());

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (graphics2D != null){

                    currentX = e.getX();
                    currentY = e.getY();
                    //graphics2D.fillOval(currentX - fillSize / 2, currentY - fillSize / 2, fillSize, fillSize);
                    fillShape(shape, currentX - fillSize / 2, currentY - fillSize / 2, fillSize, fillSize);
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
        return this.graphics2D;
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
        if (this.graphics2D != null) {
            //this.graphics2D.fillOval(x, y, this.fillSize, this.fillSize);
            //this.repaint(x, y, this.fillOvalSize, this.fillOvalSize);
            this.fillShape(this.shape, x, y, this.fillSize, this.fillSize);
            this.repaint();
        }
    }

    @Override
    public void displayFrame() throws RemoteException {

    }

    @Override
    public void setColor(Color color) throws RemoteException {
        this.graphics2D.setPaint(color);
    }

    @Override
    public void erase(int x, int y) throws RemoteException {
        if (this.graphics2D != null) {
            this.graphics2D.setPaint(Color.WHITE);
            this.graphics2D.fillOval(x, y, this.fillSize, this.fillSize);
            //this.repaint(x, y, this.fillOvalSize, this.fillOvalSize);
            this.repaint();
            this.graphics2D.setPaint(Color.BLACK);
        }
    }

    public void setFillSize(int size){
        this.fillSize = size;
    }

//    public void createShape(String shape, int x, int y, int oldX, int oldY){
//        if (shape.equals("Line")){
//            this.graphics2D.drawLine(x, y, oldX, oldY);
//        } else if (shape.equals("Rectangle")){
//            this.graphics2D.fillRect(x, y, oldX, oldY);
//        } else if (shape.equals("Oval")){
//            this.graphics2D.fillOval(x, y, oldX, oldY);
//        } else if (shape.equals("Circle")){
//            this.graphics2D.fillOval(x, y, oldX, oldY);
//        } else if (shape.equals("Square")){
//            this.graphics2D.fillRect(x, y, oldX, oldY);
//        } else {
//            this.graphics2D.fillOval(x, y, oldX, oldY);
//        }
//    }

    public void fillShape(String shape, int x, int y, int oldX, int oldY){
        if (shape.equals("Rectangle")){
            this.graphics2D.fillRect(x, y, oldX, oldY);
        } else if (shape.equals("Oval")){
            this.graphics2D.fillOval(x, y, oldX, oldY);
        } else if (shape.equals("Square")){
            this.graphics2D.fillRect(x, y, oldX, oldY);
        } else if (shape.equals("Triangle")){

            int[] xPoints = {x, x - oldX / 2, x + oldX / 2};
            int[] yPoints = {y, y + oldY, y + oldY};
            this.graphics2D.fillPolygon(xPoints, yPoints, 3);
        } else if (shape.equals("Star")){
            double radius = 50.0;
            double centerX = x;
            double centerY = y;

// The angles to the five points of the star (in radians)
            double[] angles = new double[] {0.0, 4*Math.PI/5, 8*Math.PI/5, 2*Math.PI/5, 6*Math.PI/5, 0};

            int[] xPoints = new int[6];
            int[] yPoints = new int[6];

            for (int i = 0; i < 6; i++) {
                xPoints[i] = (int)(centerX + radius * Math.cos(angles[i]));
                yPoints[i] = (int)(centerY + radius * Math.sin(angles[i]));
            }

            Polygon star = new Polygon(xPoints, yPoints, 6);
            graphics2D.fillPolygon(star);
        }
        else {
            this.graphics2D.fillOval(x, y, oldX, oldY);
        }
    }

    public void setShape(String shape){
        this.shape = shape;
    }

}


