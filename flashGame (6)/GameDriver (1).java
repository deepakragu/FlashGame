import java.awt.Color;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.io.*;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
public class GameDriver {
    public static void music(){
       try{
        URL url = new URL(
            "file:///C://Users//Asim//Desktop//flashGame/music.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });*/
       } catch(Exception e){
           e.printStackTrace();
       }
        /*try{
             Clip clip = AudioSystem.getClip();
             AudioInputStream inputStream = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream("C://Users//Asim//Desktop//flashGame/music.wav"));
             clip.open(inputStream);
             clip.start(); 
            System.out.println("playing music");
        }  catch(IOException error){
            System.out.println("exception" + error);
            error.printStackTrace();
        }
        MGP.start(loop);*/
    }
    public static void main(String[] args) {

        JFrame f = new JFrame();
        moveFlash flash = new moveFlash();
        f.setSize(900,512);
        f.add(flash);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Flashtime");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        music();
    }
}