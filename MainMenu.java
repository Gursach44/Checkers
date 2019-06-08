
/**
 * This is the class that should first be opened to play the game. It contains
 * navigation to most other classes (Settings, Instructions, and Checkers (through
 * the play button)).
 *
 * @Gurman Sachdeva
 * @June 7th
 */
//imports all necessary libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainMenu extends JFrame implements ActionListener
{   
    JButton play;
    JButton instructions;
    JButton settings;
    JButton quitgame;
    //creates the window, sets its size, and makes it visible
    public static void main (String args[])
    {
        MainMenu content=new MainMenu();
        content.setSize (1000, 900);
        content.setVisible(true);
        //plays the theme music by calling the Music class
        Music.Music("Theme.wav", Settings.musicLoop);
    }//end main
    public MainMenu()
    {   
        //creates a bckgrdclr 'color' for use as a background for objects (buttons) that use pictures
        Color bckgrdclr=new Color (38,38,38);
        JLabel checkers=new JLabel (createImageIcon ("Checkers.png"));
        //creates play button, loads its image, makes its background bckgrdclr
        //and sets and adds the actioncommand and actionlistener
        play=new JButton (createImageIcon ("Play.png"));
        play.setBackground (bckgrdclr);
        play.setBorder (null);
        play.setActionCommand ("play");
        play.addActionListener (this);
        //creates the instructions button, loads its image, makes its background bckgrdclr
        //and sets and adds the actioncommand and actionlistener
        instructions=new JButton (createImageIcon ("InstructionsIcon.png"));
        instructions.setBackground (bckgrdclr);
        instructions.setBorder (null);
        instructions.setActionCommand ("instructions");
        instructions.addActionListener (this);
        //creates the settings button, loads its image, makes its background bckgrdclr
        //and sets and adds the actioncommand and actionlistener
        settings=new JButton (createImageIcon ("SettingsIcon.png"));
        settings.setBackground (bckgrdclr);
        settings.setBorder (null);
        settings.setActionCommand ("settings");
        settings.addActionListener (this);
        //creates the quit game button, loads its image, makes its background bckgrdclr
        //and sets and adds the actioncommand and actionlistener
        quitgame=new JButton (createImageIcon ("QuitGame.png"));
        quitgame.setBackground (bckgrdclr);
        quitgame.setBorder (null);
        quitgame.setActionCommand ("quitgame");
        quitgame.addActionListener (this);
        //creates the background, loads its image, and sets its layout since
        //all the objects will be added to it
        JLabel background=new JLabel (createImageIcon("background.png"));
        background.setLayout (new GridLayout(3,1));
        //creates the bottom JPanel that will hold the navigation buttons
        JPanel bottom=new JPanel();
        bottom.setBackground (bckgrdclr);
        bottom.setBorder (null);
        //adds the game title and the play button directly to the background
        background.add (checkers);
        background.add (play);
        //adds the buttons to the bottom JPanel, then adds the JPanel to the background
        bottom.add (instructions);
        bottom.add (quitgame);
        bottom.add (settings);
        background.add (bottom);
        //adds the background
        add (background);
    }//end MainMenu
    public void actionPerformed (ActionEvent e)
    {
        //closes the game if the user has clicked the quit game button
        if (e.getActionCommand().equals ("quitgame"))
        {
            System.exit(0);
        }
        //makes this window invisible and the Checkers window visible
        //if the player has clicked the play again button
        if (e.getActionCommand().equals ("play"))
        {
            Checkers object1=new Checkers();
            object1.setVisible(true);
            object1.setSize (900, 900);
            this.setVisible(false);
        }
        //makes this window invisible and instructions window visible
        //if the player has clicked the instructions button
        if (e.getActionCommand().equals ("instructions"))
        {
            Instructions object2=new Instructions();
            object2.setVisible(true);
            object2.setSize (1000, 900);
            this.setVisible(false);
        }
        //makes this window invisible and the settings window visible
        //if the player has clicked the settings button
        if (e.getActionCommand().equals ("settings"))
        {
            Settings object3=new Settings();
            object3.setVisible(true);
            object3.setSize (900, 750);
            this.setVisible(false);
        }
    }//end actionPerformed
    //fetches images when they are called in the program, and returns the message
    //"Couldn't find the file: (path)" when the image does not exist in the program/specified folder
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL=MainMenu.class.getResource (path);
        if (imgURL !=null)
        {return new ImageIcon (imgURL);}
        else
        {System.err.println ("Couldn't find the file: "+path);
            return null;}
    }//end ImageIcon
}
