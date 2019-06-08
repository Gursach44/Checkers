
/**
 * The Settings class will contain all of the settings users can change to customize
 * their experience. This includes the colours of both of their game pieces, and the
 * state of the sound effects and music (being either on or off).
 *
 * @Gurman Sachdeva
 * @June 7th
 */
//imports all necessary libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class Settings extends JFrame implements ActionListener
{   
    //creates the two colour Strings that can alter the colour of player pieces in class Checkers
    static String colour1="white";
    static String colour2="black";
    //initializes the sound, music, and main menu buttons
    JButton sound;
    JButton music;
    JButton mainmenu;
    //creates the variables that will control sound and music states (on/off)
    static int soundLoop=1;
    static int musicLoop=1;
    //creates the sound and music image filename name strings
    String soundString;
    String musicString;
    //creates the variable that will be used to customize the position of objects
    //under the GridBag layout
    GridBagConstraints gbc=new GridBagConstraints();
    //creates the window, sets its size, and makes it visible
    public static void main (String args[])
    {
        Settings content=new Settings();
        content.setSize (900, 750);
        content.setVisible(true);
    }//end main
    public Settings()
    {   
        //creates the background wood texture image that will have all other objects placed in it, and
        //sets its layout to BorderLayout
        JLabel background=new JLabel (createImageIcon("background.png"));
        background.setLayout (new BorderLayout());
        //creates a bckgrdclr 'color' for use as a background for objects (buttons) that use pictures
        Color bckgrdclr=new Color (38,38,38);
        //creates all the unchanging title labels
        JLabel settings=new JLabel (createImageIcon ("Settings.png"));
        JLabel Plr1Clr=new JLabel(createImageIcon ("Plr1Clr.png"));
        JLabel Plr2Clr=new JLabel(createImageIcon ("Plr2Clr.png"));
        JLabel Sound=new JLabel(createImageIcon ("Sound.png"));
        //This set of if statements ensures that the buttons display correctly
        //after the settings window is closed and opened again
        if (soundLoop==1)
        {soundString="SoundOn.png";}
        else 
        {soundString="SoundOff.png";}
        if (musicLoop==1)
        {musicString="MusicOn.png";}
        else 
        {musicString="MusicOff.png";}
        //all of the radio buttons containing options for players to set their piece colours
        //are created, their actioncommand is set to their colour for conveniently calling
        //their respective piece images later, their actionlistener is set, and their 
        //background colour is set to match the background of the rest of the window
        JRadioButton black1=new JRadioButton ("Black");
        black1.setActionCommand ("black");
        black1.addActionListener (this);
        black1.setForeground (Color.black);
        black1.setBackground (bckgrdclr);
        JRadioButton white1=new JRadioButton ("White");
        white1.setActionCommand ("white");
        white1.addActionListener (this);
        white1.setForeground (Color.white);
        white1.setBackground (bckgrdclr);
        JRadioButton red1=new JRadioButton ("Red");
        red1.setActionCommand ("red");
        red1.addActionListener (this);
        red1.setForeground (Color.red);
        red1.setBackground (bckgrdclr);
        JRadioButton orange1=new JRadioButton ("Orange");
        orange1.setActionCommand ("orange");
        orange1.addActionListener (this);
        orange1.setForeground (Color.orange);
        orange1.setBackground (bckgrdclr);
        JRadioButton yellow1=new JRadioButton ("Yellow");
        yellow1.setActionCommand ("yellow");
        yellow1.addActionListener (this);
        yellow1.setForeground (Color.yellow);
        yellow1.setBackground (bckgrdclr);
        JRadioButton green1=new JRadioButton ("Green");
        green1.setActionCommand ("green");
        green1.addActionListener (this);
        green1.setForeground (Color.green);
        green1.setBackground (bckgrdclr);
        JRadioButton blue1=new JRadioButton ("Blue");
        blue1.setActionCommand ("blue");
        blue1.addActionListener (this);
        blue1.setForeground (Color.blue);
        blue1.setBackground (bckgrdclr);
        JRadioButton purple1=new JRadioButton ("Purple");
        purple1.setActionCommand ("purple");
        purple1.addActionListener (this);
        purple1.setForeground (Color.magenta);
        purple1.setBackground (bckgrdclr);
        purple1.setBackground (bckgrdclr);
        JRadioButton black2=new JRadioButton ("Black");
        black2.setActionCommand ("black2");
        black2.addActionListener (this);
        black2.setForeground (Color.black);
        black2.setBackground (bckgrdclr);
        JRadioButton white2=new JRadioButton ("White");
        white2.setActionCommand ("white2");
        white2.addActionListener (this);
        white2.setForeground (Color.white);
        white2.setBackground (bckgrdclr);
        JRadioButton red2=new JRadioButton ("Red");
        red2.setActionCommand ("red2");
        red2.addActionListener (this);
        red2.setForeground (Color.red);
        red2.setBackground (bckgrdclr);
        JRadioButton orange2=new JRadioButton ("Orange");
        orange2.setActionCommand ("orange2");
        orange2.addActionListener (this);
        orange2.setForeground (Color.orange);
        orange2.setBackground (bckgrdclr);
        JRadioButton yellow2=new JRadioButton ("Yellow");
        yellow2.setActionCommand ("yellow2");
        yellow2.addActionListener (this);
        yellow2.setForeground (Color.yellow);
        yellow2.setBackground (bckgrdclr);
        JRadioButton green2=new JRadioButton ("Green");
        green2.setActionCommand ("green2");
        green2.addActionListener (this);
        green2.setForeground (Color.green);
        green2.setBackground (bckgrdclr);
        JRadioButton blue2=new JRadioButton ("Blue");
        blue2.setActionCommand ("blue2");
        blue2.addActionListener (this);
        blue2.setForeground (Color.blue);
        blue2.setBackground (bckgrdclr);
        JRadioButton purple2=new JRadioButton ("Purple");
        purple2.setActionCommand ("purple2");
        purple2.addActionListener (this);
        purple2.setForeground (Color.magenta);
        purple2.setBackground (bckgrdclr);
        //two button groups are created and all of the radioButtons are added to their
        //respective group, to allow the user to select only one colour at a time
        ButtonGroup Colours1=new ButtonGroup();
        Colours1.add(black1);
        Colours1.add(white1);
        Colours1.add(red1);
        Colours1.add(orange1);
        Colours1.add(yellow1);
        Colours1.add(green1);
        Colours1.add(blue1);
        Colours1.add(purple1);
        ButtonGroup Colours2=new ButtonGroup();
        Colours2.add(black2);
        Colours2.add(white2);
        Colours2.add(red2);
        Colours2.add(orange2);
        Colours2.add(yellow2);
        Colours2.add(green2);
        Colours2.add(blue2);
        Colours2.add(purple2);
        //creates the sound on/off button, loading in its picture, setting its background colour,
        //setting its actionCommand, and setting its actionListener
        sound=new JButton (createImageIcon (soundString));
        sound.setBackground (bckgrdclr);
        sound.setBorder (null);
        sound.setActionCommand ("sound");
        sound.addActionListener (this);
        //creates the music on/off button, loading in its picture, setting its background colour,
        //setting its actionCommand, and setting its actionListener
        music=new JButton (createImageIcon (musicString));
        music.setBackground (bckgrdclr);
        music.setBorder (null);
        music.setActionCommand ("music");
        music.addActionListener (this);
        //creates the main menu button, loading in its picture, setting its background colour,
        //setting its actionCommand, and setting its actionListener
        mainmenu=new JButton (createImageIcon ("MainMenu.png"));
        mainmenu.setBackground (bckgrdclr);
        mainmenu.setBorder (null);
        mainmenu.setActionCommand ("mainmenu");
        mainmenu.addActionListener (this);
        //creates 3 JPanels, west, center, and east, to hold different chunks of objects in the background
        JPanel west=new JPanel();
        JPanel center=new JPanel();
        JPanel east=new JPanel();
        //sets the west and center panels to have GridBagLayout, to hold the radioButtons later
        west.setLayout (new GridBagLayout ());
        center.setLayout (new GridBagLayout ());
        //sets the layout of the east panel to GridLayout
        east.setLayout (new GridLayout (4, 1, 75, 10));
        //sets the background colours of all these panels to match the background
        west.setBackground (bckgrdclr);
        center.setBackground (bckgrdclr);
        east.setBackground (bckgrdclr);
        //changes the GridBagConstraints to position the player 1 and 2 colour titles further from
        //the radioButtons than the other radioButtons, sets which cell is being added to,
        //and finally adds the titles to their respective JPanels
        gbc.insets=new Insets (5,50,5,50);
        gbc.gridx=1;
        gbc.gridy=0;
        west.add (Plr1Clr, gbc);
        gbc.gridx=0;
        center.add (Plr2Clr, gbc);
        //adds the title and buttons in the east JPanel
        east.add (Sound);
        east.add (sound);
        east.add (music);
        east.add (mainmenu);
        //now begins adding all of the radioButtons to their respective JPanels
        gbc.gridx=1;
        gbc.gridy=7;
        west.add(black1, gbc);
        gbc.gridy=8;
        west.add(white1, gbc);
        gbc.gridy=9;
        west.add(red1, gbc);
        gbc.gridy=10;
        west.add(orange1, gbc);
        gbc.gridy=11;
        west.add(yellow1, gbc);
        gbc.gridy=12;
        west.add(green1, gbc);
        gbc.gridy=13;
        west.add(blue1, gbc);
        gbc.gridy=14;
        west.add(purple1, gbc);
        gbc.gridx=0;
        gbc.gridy=7;
        center.add(black2, gbc);
        gbc.gridy=8;
        center.add(white2, gbc);
        gbc.gridy=9;
        center.add(red2, gbc);
        gbc.gridy=10;
        center.add(orange2, gbc);
        gbc.gridy=11;
        center.add(yellow2, gbc);
        gbc.gridy=12;
        center.add(green2, gbc);
        gbc.gridy=13;
        center.add(blue2, gbc);
        gbc.gridy=14;
        center.add(purple2, gbc);
        //adds all of the JPanels to the background, in their respective positions in BorderLayout,
        //and adds the background to the JFrame
        background.add (settings, BorderLayout.NORTH);
        background.add (west, BorderLayout.WEST);
        background.add (center, BorderLayout.CENTER);
        background.add (east, BorderLayout.EAST);
        add (background);
    }//end Settings
    public void actionPerformed (ActionEvent e)
    {
        //runs if the player has clicked the sound button
        if (e.getActionCommand().equals("sound"))
        {
            //runs if the sound button displayed on, and makes it display off
            //as well as changes the loop value to off
            if (soundLoop==1)
            {
                sound.setIcon (createImageIcon ("SoundOff.png"));
                soundLoop=0;
                soundString="SoundOff.png";
            }
            //runs if the sound button displayed off, and makes it display on
            //as well as changes the loop value to on
            else
            {
                sound.setIcon (createImageIcon ("SoundOn.png"));
                soundLoop=1;
                soundString="SoundOn.png";
            }
        }
        else if (e.getActionCommand().equals("music"))
        {
            //runs if the music button displayed on, and makes it display off
            //as well as changes the loop value to off
            if (musicLoop==1)
            {
                music.setIcon (createImageIcon ("MusicOff.png"));
                musicLoop=0;
                musicString="MusicOff.png";
                Music.Music("Theme.wav", Settings.musicLoop);
            }
            //runs if the music button displayed off, and makes it display on
            //as well as changes the loop value to on
            else
            {
                music.setIcon (createImageIcon ("MusicOn.png"));
                musicLoop=1;
                musicString="MusicOn.png";
                Music.Music("Theme.wav", Settings.musicLoop);
            }
        }
        //runs if the main menu button was clicked, setting this window invisible, 
        //and setting the main menu window visible
        else if (e.getActionCommand().equals ("mainmenu"))
        {
            MainMenu object=new MainMenu();
            object.setVisible(true);
            object.setSize (1000, 900);
            this.setVisible(false);
        }
        //runs if any of the player 1 colour chooser radioButtons were clicked
        else if ((e.getActionCommand().equals ("black"))||(e.getActionCommand().equals ("white"))||
        (e.getActionCommand().equals ("red"))||(e.getActionCommand().equals ("orange"))||
        (e.getActionCommand().equals ("yellow"))||(e.getActionCommand().equals ("green"))
        ||(e.getActionCommand().equals ("blue"))||(e.getActionCommand().equals ("purple")))
        {
            //sets the colour1 string to the actionCommand of the clicked radioButton
            colour1=(e.getActionCommand());
        }
        //since the only other possible buttons to be clicked here are the player 2 colour chooser
        //radioButtons, the actionCommand does not need to be checked, and this will only run
        //when the user has clicked one of these buttons
        else
        //sets the colour2 string to the actionCommand of the clicked radioButton
        {colour2=(e.getActionCommand());}
    }//end actionPerformed

    //fetches images when they are called in the program, and returns the message
    //"Couldn't find the file: (path)" when the image does not exist in the program/specified folder
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL=Settings.class.getResource (path);
        if (imgURL !=null)
        {return new ImageIcon (imgURL);}
        else
        {System.err.println ("Couldn't find the file: "+path);
            return null;}
    }//end ImageIcon
}

