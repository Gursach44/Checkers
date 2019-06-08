
/**
 * The WinMenu class will run when a player has won the game, and will not run if opened from the 
 * BlueJ package sceen, since it requires a value to be set to run, which is only set when someone wins
 *
 * @Gurman Sachdeva
 * @June 7th
 */
//imports all necessary libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class WinMenu extends JFrame implements ActionListener
{   
    //creates the label whose text will be altered when a player wins in Class Checkers
    static JLabel winner=new JLabel ("", SwingConstants.CENTER);
    //initializes the quit game, play, and main menu buttons
    JButton quitgame;
    JButton play;
    JButton mainmenu;
    //creates the window, sets its size, and makes it visible
    public static void main (String args[])
    {
        WinMenu content=new WinMenu();
        content.setSize (1000, 575);
        content.setVisible(true);
    }//end main
    public WinMenu()
    {   
        //creates a transparent 'color' for use as a background for objects
        Color transparent=new Color (0,0,0,0);
        //checks to run when the winner text has changed
        if (!Checkers.winner.equals ("blank"))
        {
            //sets the text as the winner String in class Checkers
            winner.setText (Checkers.winner);
        }
        //sets the font size and type to be large on the screen
        winner.setFont (new Font ("Arial", Font.BOLD, 65));
        //creates the quit game button, loading its image in, setting
        //its actionCommand and actionListener, and omitting its background and border
        quitgame=new JButton (createImageIcon ("QuitGame.png"));
        quitgame.setBackground (transparent);
        quitgame.setBorder (null);
        quitgame.setActionCommand ("quitgame");
        quitgame.addActionListener (this);
        //creates the play again button, loading its image in, setting
        //its actionCommand and actionListener, and omitting its background and border
        play=new JButton (createImageIcon ("PlayAgain.png"));
        play.setBackground (transparent);
        play.setBorder (null);
        play.setActionCommand ("play");
        play.addActionListener (this);
        //creates the main menu button, loading its image in, setting
        //its actionCommand and actionListener, and omitting its background and border
        mainmenu=new JButton (createImageIcon ("MainMenu.png"));
        mainmenu.setBackground (transparent);
        mainmenu.setBorder (null);
        mainmenu.setActionCommand ("mainmenu");
        mainmenu.addActionListener (this);
        //creates a JPanel to hold all the buttons in the bottom section of the window
        JPanel bottom=new JPanel ();
        bottom.setBackground (transparent);
        //creates the background, loads its picture in, and sets its layout
        JLabel background=new JLabel (createImageIcon("winner.png"));
        background.setLayout (new GridLayout(3,1));
        //adds the winner text to the background, adds the buttons to the bottom JPanel,
        //adds this JPanel to the background, and adds the background itself
        background.add (winner);
        bottom.add (quitgame);
        bottom.add (play);
        bottom.add (mainmenu);
        background.add (bottom);
        add (background);
    }//end WinMenu
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
        //makes this window invisible and the main menu window visible
        //if the player has clicked the main menu button
        if (e.getActionCommand().equals ("mainmenu"))
        {
            MainMenu object2=new MainMenu();
            object2.setVisible(true);
            object2.setSize (1000, 900);
            this.setVisible(false);
        }
    }//end actionPerformed
    //fetches images when they are called in the program, and returns the message
    //"Couldn't find the file: (path)" when the image does not exist in the program/specified folder
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL=WinMenu.class.getResource (path);
        if (imgURL !=null)
        {return new ImageIcon (imgURL);}
        else
        {System.err.println ("Couldn't find the file: "+path);
            return null;}
    }//end ImageIcon
}
