import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;

public class moveFlash extends JPanel implements ActionListener, KeyListener {
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 512;
    Timer t = new Timer(5, this);
    double x = 0, y = 0, velX; //velY = 0;
    private Image image;
    private Image image2;
    private Image background; 
    private Image image3;
    Square[] squareArray = new Square[4];
    private boolean isLightning;
    private Image lightning;
    private Object lock = new Object();
    private ArrayList<lightningThrow> l = new ArrayList<lightningThrow>();
    private lightningThrow tempLightning;

    public moveFlash(){
        t.start(); 
        addKeyListener(this);
        this.setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        image = Toolkit.getDefaultToolkit().getImage("flashMove.gif");
        image2 = Toolkit.getDefaultToolkit().getImage("flashMove2.gif");
        image3 = Toolkit.getDefaultToolkit().getImage("flashStand.gif");
        background = Toolkit.getDefaultToolkit().createImage("centralCity.jpg");
        lightning = Toolkit.getDefaultToolkit().getImage("lightning.gif");
        isLightning = false;
        for (int i = 0; i < squareArray.length; i++){
            squareArray[i] = new Square();
        }
        x = 450;
        y = 542;
    }

    public void paint(Graphics g){

        Image img4 = background;
        Image img1 = image;
        Image img2 = image2;
        Image img3 = image3;
        Image img5 = lightning;

        int imageWidth = img3.getWidth(null);
        int imageHeight = img3.getHeight(null);

        int x1 = (int) x;
        int y1 = (int) y;
        g.drawImage(img4, 0, 0, null);
        if(velX == 1.5){
            g.drawImage(img1, (x1-imageWidth), (y1-imageHeight), x1, y1, 0, 0, (imageWidth*3), (imageHeight*3), null);
        }
        if(velX == -1.5){
            g.drawImage(img2, (x1-imageWidth), (y1-imageHeight), x1, y1, 0, 0, (imageWidth*3), (imageHeight*3), null);
        }
        if(velX == 0){
            g.drawImage(img3, (x1-imageWidth), (y1-imageHeight), x1, y1, 0, 0, (imageWidth*2), (imageHeight*2), null);
        }
        for (Square aSquareArray : squareArray) {
            aSquareArray.update();
        }
          
        synchronized(lock) {
            java.util.List<lightningThrow> toRemove = new ArrayList<lightningThrow>();
            if(l.size() > 0){
                for (lightningThrow i : l) {
                    if(i.returnYLocation() < 0){
                        toRemove.add(i);
                    } else {
                        i.update();
                    }
                }
            }
            l.removeAll(toRemove);
        }
        
        for (Square aSquareArray : squareArray){
            Image img10 = aSquareArray.returnImage();
            int w = img10.getWidth(null);
            int h = img10.getHeight(null);
            // System.out.println("Square x = " + aSquareArray.returnXLocation() + " y = " +  (int) aSquareArray.returnYLocation() + " w = " + w + " h = " + h);
   
            Rectangle r = new Rectangle(aSquareArray.returnXLocation(), (int) aSquareArray.returnYLocation(), aSquareArray.getSquareSize(),aSquareArray.getSquareSize() ); 
            for(int j = 0; j < l.size(); j++) {
                Image img11 = l.get(j).returnImage();
                int wi = img11.getWidth(null);
                int hi = img11.getHeight(null);
                int c = l.get(j).returnXLocation();
                double d = l.get(j).returnYLocation();
                Rectangle p = new Rectangle(c - 100, (int) d - 30, (int) 20, (int) 40);
                //System.out.println("Square x = " + aSquareArray.returnXLocation() + " y = " +  (int) aSquareArray.returnYLocation() + " w = " + w + " h = " + h + "    Lightning x = " + c + " y = " +  d + "isintersect = " + r.intersects(p));
                
                if (r.intersects(p)) {
                    aSquareArray.DoCollision();
                    aSquareArray.update();
                    l.remove(j);
                    j--;
                }
                
                
                
            }
        }
 
        for (Square aSquareArray : squareArray) {
            aSquareArray.paint(g);
        }
        if(l.size() > 0){
            for(lightningThrow i : l){
                i.paint(g);
            }
        }
    }

    public void update() {
        x += velX;
        repaint();
        x += velX;
    }

    public void actionPerformed(ActionEvent e){

        x += velX;

        if(x<96){
            velX = 0;
            x = 96;
        }

        if(x>950){
            velX = 0;
            x = 950;
        }
        repaint();
    }


    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT){
            velX = -1.5;
        }

        if (code == KeyEvent.VK_RIGHT){
            velX = 1.5;
        }
        if(code == KeyEvent.VK_UP){
            isLightning = true;
            tempLightning = new lightningThrow((int) x, (int) y);
            synchronized(lock) {
              l.add(tempLightning);
            }
        }
        if(code == KeyEvent.VK_SPACE){
            for (Square aSquareArray : squareArray) {
                aSquareArray.setFallSpeed(0.1);
            }
        }
    }

    public void keyTyped(KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT){
            velX = -1.5;
        }

        if (code == KeyEvent.VK_RIGHT){
            velX = 1.5;
        }
        if(code == KeyEvent.VK_UP){
            isLightning = true;
            tempLightning = new lightningThrow((int) x, (int) y);
            l.add(tempLightning);
        }
        if(code == KeyEvent.VK_SPACE){
            for (Square aSquareArray : squareArray) {
                aSquareArray.setFallSpeed(0.1);
            }
        }
    }

    public void keyReleased(KeyEvent e){

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT){
            velX = 0;
        }
        if (code == KeyEvent.VK_RIGHT){
            velX = 0;
        }
        if (code == KeyEvent.VK_UP){
            isLightning = false;
        }
        if(code == KeyEvent.VK_SPACE){
            for (Square aSquareArray : squareArray) {
                aSquareArray.generateRandomFallSpeed();
            }
        }
    }

}