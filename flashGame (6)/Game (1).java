import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Game extends JPanel {

    //changing these values will change the size of the game, while still remaining functional
    //within the size limit specified.
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 512;

    //Creates a Square object Array
    Square[] squareArray = new Square[3];

    private static moveFlash flash;

    Image img = Toolkit.getDefaultToolkit().createImage("centralCity.jpg");

    public Game() {
        //moveFlash move = new moveFlash();

        //initializes square objects
        for (int i = 0; i < squareArray.length; i++)
            squareArray[i] = new Square();
    }

    public void paint(Graphics g)
    {
        // Draws the img to the BackgroundPanel.
        g.drawImage(img, 0, 0, null);
        for (Square aSquareArray : squareArray) {
            aSquareArray.paint(g);
        }
        flash.paint(g);
    }

    /**
    public void paint(Graphics graphics) {

    //makes background black
    graphics.setColor(Color.black);
    graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

    //paints square objects to the screen
    for (Square aSquareArray : squareArray) {
    aSquareArray.paint(graphics);
    }
    }
     */
    public void update() {

        //calls the Square class update method on the square objects
        for (Square aSquareArray : squareArray) aSquareArray.update();
        flash.update();

    }

    public static void main(String[] args) throws InterruptedException {
        /**
        JFrame f = new JFrame();

        f.add(g);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,600);
         */

        Game game = new Game();
        flash = new moveFlash();
        JFrame frame = new JFrame();
        frame.add(game);
        game.repaint();

        
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Flashtime");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        while (true) {
            game.update();
            game.repaint();
            Thread.sleep(10);
        }
    }
}


