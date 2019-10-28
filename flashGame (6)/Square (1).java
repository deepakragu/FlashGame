import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * To Do List
 * Arrow Keys movement w/ Flash & animate Flash Object
 * When "f" key pressed for flash time, need to have screen briefly flash
 *     and then objects start moving slowly --> need 2 methods for fall speed,
 *     one for slow speed and one for fast
 * Also make diff square objects but like should be same size except for maybe
 *     like boss objects so larger rock falls at slower speed but each object
 *     should be same size at diff spots & speeds
 */
public class Square extends JPanel {

    private int squareXLocation;
    private double squareSize;
    private double squareYLocation = -squareSize;
    private double fallSpeed = 1;
    Random rand = new Random();
    Image debrisImage;

    /*
    //creates a random value inside the window and stores it in squareXLocation
     */
    public int generateRandomXLocation(){
        return squareXLocation = rand.nextInt(950 - (int) squareSize);
    }

    /*
    //creates a random value between 1-50 and stores it in squareWidth
     */
    public double generateRandomSquareSize(){
        return squareSize = Math.random()*20+40;
    }

    /*
    //creates a random value that is not zero and stores it in fallSpeed(so squares do not get stuck at 0 speed)
     */
    public double generateRandomFallSpeed(){
        double rangeMin = 0.1;
        double rangeMax = 1.5;
        Random r = new Random();
        fallSpeed = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return fallSpeed;
    }

    public void generateRandomImage() {
        Image[] imageList = new Image[4];
        imageList[0] = Toolkit.getDefaultToolkit().getImage("fallingTree.png");
        imageList[1] = Toolkit.getDefaultToolkit().getImage("brokenCar.png");
        imageList[2] = Toolkit.getDefaultToolkit().getImage("hotdogStand.png");
        imageList[3] = Toolkit.getDefaultToolkit().getImage("rock.png");
        double K = Math.random()*4;
        debrisImage = imageList[(int) K];
    }
    public int getSquareSize(){
        return (int) squareSize;
    }
    /*
    //paints the square with the variables generated in the random methods
     */
    public void paint(Graphics g){
        //Image img = Toolkit.getDefaultToolkit().getImage("fallingTree.jpg");
        /*Image img1 = img.getScaledInstance(-1,(int) squareSize, java.awt.Image.SCALE_SMOOTH);
        img = img1;*/ 
        
        Image img1 = debrisImage;
        
        int width = (int) squareSize;
        int height = (int) squareSize;
        
        int imageWidth = img1.getWidth(null);
        int imageHeight = img1.getHeight(null);
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
        
        g.drawImage(img1, x, y, (x+width), (y+height), 0, 0, imageWidth, imageHeight,null);
        
        /**
        g.setColor(Color.CYAN);
        g.fillRect(squareXLocation, (int) squareYLocation, (int) squareSize, (int) squareSize);
         */
    }
    public void DoCollision(){
        squareYLocation = 542;
    }
    /*
    //sets the squareWidth and square fallSpeed to a random value for every square created
     */
    public Square(){
        generateRandomXLocation();
        generateRandomSquareSize();
        generateRandomFallSpeed();
        generateRandomImage();
    }

    public void update(){

        //changes the squares xLocation and fallSpeed if the created square reaches the bottom of the screen
        if(squareYLocation >= 542){
            generateRandomXLocation();
            generateRandomFallSpeed();
            generateRandomSquareSize();
            generateRandomImage();
            squareYLocation = -squareSize;
        }

        //moves the square down if the square is inside the window
        if(squareYLocation <= 542){
            squareYLocation += fallSpeed;
        }
    }
    public void setFallSpeed(double n){
        fallSpeed = n;
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
        return debrisImage;
    }

}