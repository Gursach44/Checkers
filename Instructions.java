
/**
 * The Instructions class will create and display the instructions screen to the players. This will simply show the
 * players the instructions, and include a main menu button to go back to the menu and begin playing.
 *
 * @Gurman Sachdeva
 * @June 7th
 */
//imports all necessary libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions extends JFrame implements ActionListener
{   
    //initializes the main menu button
    JButton mainmenu;
    //creates the window, sets its size, and makes it visible
    public static void main (String args[])
    {
        Instructions content=new Instructions();
        content.setSize (1000, 900);
        content.setVisible(true);
    }//end main
    public Instructions()
    {   
        //creates a bckgrdclr 'color' for use as a background for bottom JPanel section
        Color bckgrdclr=new Color (38,38,38);
        //creates a transparent 'color' for use as a background for middle JPanel section
        Color transparent=new Color (0,0,0,0);
        //creates the Instructions title JLabel
        JLabel instructions=new JLabel (createImageIcon ("Instructions.png"));
        //This series of JLabels creates all the labels needed to hold the instructions text. Every label
        //is created with white text, using Arial font, and holds a chunk of text that just barely fits in the window
        JLabel instprinted=new JLabel ("There are two players, each with 12 pieces on their respective sides of an 8x8 alternating", SwingConstants.CENTER);
        instprinted.setForeground (Color.white);
        instprinted.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted2=new JLabel ("black and white-tiled checkerboard. Players must move a single piece to any adjacent black", SwingConstants.CENTER);
        instprinted2.setForeground (Color.white);
        instprinted2.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted3=new JLabel ("tile, away from their starting side. All pieces will remain on black tiles throughout the game.", SwingConstants.CENTER);
        instprinted3.setForeground (Color.white);
        instprinted3.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted4=new JLabel ("Players must try to capture the other player’s pieces by jumping over them to another black", SwingConstants.CENTER);
        instprinted4.setForeground (Color.white);
        instprinted4.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted5=new JLabel ("tile behind the captured piece. If a player manages to get one of their pieces to the opponent’s", SwingConstants.CENTER);
        instprinted5.setForeground (Color.white);
        instprinted5.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted6=new JLabel ("side, it becomes a ‘king’ and is granted the ability to move backwards. The end goal of the game", SwingConstants.CENTER);
        instprinted6.setForeground (Color.white);
        instprinted6.setFont (new Font("Arial", Font.PLAIN, 20));
        JLabel instprinted7=new JLabel ("is to capture all opponent pieces. Good Luck!", SwingConstants.CENTER);
        instprinted7.setForeground (Color.white);
        instprinted7.setFont (new Font("Arial", Font.PLAIN, 20));
        //creates the main menu button, loading in its picture, setting its background colour,
        //setting its actionCommand, and setting its actionListener
        //the setBorder (null) line omits the button's border for a cleaner look
        mainmenu=new JButton (createImageIcon ("MainMenu.png"));
        mainmenu.setBackground (bckgrdclr);
        mainmenu.setBorder (null);
        mainmenu.setActionCommand ("mainmenu");
        mainmenu.addActionListener (this);
        //the background wood texture image label is created and loaded with the image, and
        //since it will hold all the other components of the screen its layout is set
        JLabel background=new JLabel(createImageIcon("background.png"));
        background.setLayout (new GridLayout(3,1));
        //the middle section of the frame, that will hold all the instructions text,
        //will house a JPanel to contain all the JLabels. Its layout is also set
        JPanel middle=new JPanel (new GridLayout(7,1));
        middle.setBackground (transparent);
        //all the instructions text JLabels are added to the middle JPanel,
        //and all the other objects are added directly to the background, then the background is added
        middle.add (instprinted);
        middle.add (instprinted2);
        middle.add (instprinted3);
        middle.add (instprinted4);
        middle.add (instprinted5);
        middle.add (instprinted6);
        middle.add (instprinted7);
        background.add (instructions);
        background.add (middle);
        background.add (mainmenu);
        add (background);
    }//end Instructions
    public void actionPerformed (ActionEvent e)
    {
        //this will run if the user has clicked the main menu button
        //it sets this screen to invisible, and sets the main menu screen to visible
        if (e.getActionCommand().equals ("mainmenu"))
        {
            MainMenu object=new MainMenu();
            object.setVisible(true);
            object.setSize (1000, 900);
            this.setVisible(false);
        }
    }//end actionPerformed
    //fetches images when they are called in the program, and returns the message
    //"Couldn't find the file: (path)" when the image does not exist in the program/specified folder
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL=Instructions.class.getResource (path);
        if (imgURL !=null)
        {return new ImageIcon (imgURL);}
        else
        {System.err.println ("Couldn't find the file: "+path);
            return null;}
    }//end ImageIcon
}
