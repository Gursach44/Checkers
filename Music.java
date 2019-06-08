
/**
 * This class contains the music file to be played by other classes.
 * They will play depending on the state of music (on/off), as chosen
 * by the user in the Settings class.
 *
 * MOST FUNCTIONAL CODE IN THIS CLASS SOURCED FROM
 * https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 *
 * @Gurman Sachdeva
 * @June 7th
 */
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class Music extends JFrame 
{
    //this static variable is created to allow the stopping of the audio clip
    static Clip clip;
    public static void Music(String file, int musicLoop) {
        if (musicLoop==1)
        {
            try 
            {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResource(file));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
            catch (Exception e) 
            {}
        } 
        else 
        {clip.stop();}
    }
}
