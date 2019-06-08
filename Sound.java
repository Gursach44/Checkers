
/**
 * This class contains the sound file to be played by other classes.
 * They will play depending on the state of sound (on/off), as chosen
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
public class Sound extends JFrame 
{
    public Sound() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        if (Settings.soundLoop==1)
        {
            try 
            {
                URL url = this.getClass().getClassLoader().getResource("sfx.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                if (Settings.soundLoop==0)
                {clip.stop();}
                else
                {clip.start();}
            } 
            catch (UnsupportedAudioFileException e) 
            {e.printStackTrace();}
            catch (IOException e) 
            {e.printStackTrace();} 
            catch (LineUnavailableException e) 
            {e.printStackTrace();}
        }
        else
        {dispose();}
    }
}