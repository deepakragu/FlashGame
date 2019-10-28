import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * 
 */
public class lightningThrow extends JPanel {

    private int squareXLocation;
    private double squareSize;
    private double squareYLocation;
    private double fallSpeed;
    Random rand = new Random();
    Image lightningImage;
    /*
    //sets the squareWidth and square fallSpeed to a random value for every square created
     */
    public lightningThrow(){
       squareXLocation = 0;
       lightningImage = Toolkit.getDefaultToolkit().getImage("lightning1.gif"); 
    }

    public lightningThrow(int n, int m) {
        squareXLocation = n;
        squareYLocation = m;
        lightningImage = Toolkit.getDefaultToolkit().getImage("lightning1.gif");
    }
    
    /*
    //paints the square with the variables generated in the random methods
     */
    public void paint(Graphics g){
        //Image img = Toolkit.getDefaultToolkit().getImage("fallingTree.jpg");
        /*Image img1 = img.getScaledInstance(-1,(int) squareSize, java.awt.Image.SCALE_SMOOTH);
        img = img1;*/ 
        
        
        
        int width = 20;
        int height = 40;
        
        int imageWidth = lightningImage.getWidth(null);
        int imageHeight = lightningImage.getHeight(null);
        /*
        double originalImageRatio = imageWidth / (double) imageHeight;
        double scaledImageRatio = width / (double) height;
        
        Image img12;
        img1 = img.getScaledInstance((int) squareSize,(int) squareSize, Image.SCALE_SMOOTH);
        */
        /*
        if(imageHeight - (Math.abs(imageWidth - width) / originalImageRatio) <= height) {
            img1 = img.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
        } else if(imageWidth - (Math.abs(imageHeight - height) * originalImageRatio) <= width) {
            img1 = img.getScaledInstance(-1, height, Image.SCALE_SMOOTH);
        }
        */
        int x = squareXLocation;
        int y = (int) squareYLocation;
        
        g.drawImage(lightningImage, x - 115, y - 130, (x + 30),(y - 200), 0, 0, imageWidth*2, imageHeight * 2, null);

        /**
        g.setColor(Color.CYAN);
        g.fillRect(squareXLocation, (int) squareYLocation, (int) squareSize, (int) squareSize);
         */
    }

    public void update() {
       squareYLocation -= 2;
           
    }
    
    public void setYLocation(double n) {
        squareYLocation = n;
    }
    
    public double returnYLocation() {
        return squareYLocation;
    }
    
    public void setXLocation(int n) {
        squareXLocation = n;
    }
    
    public int returnXLocation() {
        return squareXLocation;
    }
    
    public Image returnImage() {
        lightningImage = Toolkit.getDefaultToolkit().getImage("lightning1.gif");
        return lightningImage;
    }
    
}