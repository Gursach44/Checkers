
/**
 * This class contains the actual game Checkers. It creates an 8x8 board and
 * a value for each tile on this board using two 2-dimensional arrays, and
 * simulates a game of checkers between two players, with 12 pieces each
 * on either side of the board. Most rules in this version of the game
 * align with the 'English Draughts' version of checkers.
 *
 * @Gurman Sachdeva
 * @June 7th
 */
//imports all necessary libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Checkers extends JFrame implements ActionListener
{   
    //initializes the main menu button and info JLabel
    JButton mainmenu;
    JLabel info;
    //initializes the group of JLabels that will show how many pieces have been captured
    //by player 1
    JLabel captured1;
    JLabel capturedIcon1=new JLabel (createImageIcon(Settings.colour2+".png"));
    int pcsNum1=0;
    JLabel pcs1;
    //initializes the group of JLabels that will show how many pieces have been captured
    //by player 2
    JLabel captured2;
    JLabel capturedIcon2=new JLabel (createImageIcon(Settings.colour1+".png"));
    int pcsNum2=0;
    JLabel pcs2;
    //creates the turn variable, setting its initial value at 1 indicating it is player 1's turn
    int turn=1;
    //creates the two 2-dimensional (8x8) arrays, one for the checkerboard and its pieces, and
    //one for the direct mapping of the value of the pieces on the board
    //they are actually 9x9 but were made easier to understand as nothing was added to index 0 in these arrays
    JButton btn[] [] = new JButton[9] [9];
    int btnval[] []=new int[9] [9];
    //creates the two ints that will dictate the piece to be moved, and the tile to move to
    int moveTo=0;
    int toMove=0;
    //creates the int that will store the value of the actionCommand when a tile or piece is clicked
    int positionInt;
    //creates the two ints that will retrive and store the individual x and y-values from positionInt
    int xval;
    int yval;
    //initializes the string that will say who has won
    //this is static since it will be accessed by class WinMenu to display who has won
    static String winner;
    //creates the window, sets its size, and makes it visible
    public static void main (String args[])
    {
        Checkers content=new Checkers();
        content.setSize (900, 900);
        content.setVisible(true);
    }//end main
    public Checkers()
    {   
        //sets the layout of the window to BorderLayout
        setLayout (new BorderLayout());
        //creates a bckgrdclr 'color' for use as a background for objects (buttons) that use pictures
        Color bckgrdclr=new Color (38,38,38);
        //sets the background of this screen as the background colour
        setBackground (bckgrdclr);
        //creates the center JPanel with an 8x8 grid, since it will hold the checkboard
        JPanel center=new JPanel (new GridLayout (8,8));
        //sets the size of the checker board
        center.setPreferredSize (new Dimension(640,640));
        center.setSize (new Dimension(640,640));
        JPanel north=new JPanel ();
        north.setBackground (bckgrdclr);
        JPanel south=new JPanel (new GridLayout (1,3));
        south.setBackground (bckgrdclr);
        //creates filler panels for the east and west sides of the window to give the game board
        //a border
        JLabel westfill=new JLabel ("FILLERFILLERFILLE");
        westfill.setForeground (bckgrdclr);
        JPanel west=new JPanel();
        JLabel eastfill=new JLabel ("FILLERFILLERFILLE");
        eastfill.setForeground (bckgrdclr);
        JPanel east=new JPanel();
        west.setBackground (bckgrdclr);
        east.setBackground (bckgrdclr);
        west.add (westfill);
        east.add (eastfill);
        add (west, BorderLayout.WEST);
        add (east, BorderLayout.EAST);
        //creates main menu button, loads its image, makes its background bckgrdclr
        //and sets and adds the actioncommand and actionlistener
        mainmenu=new JButton (createImageIcon ("MainMenu.png"));
        mainmenu.setBackground (bckgrdclr);
        mainmenu.setBorder (null);
        mainmenu.setActionCommand ("mainmenu");
        mainmenu.addActionListener (this);
        //creates the two labels indicating how many pieces the player has captured,
        //sets their background colour to bckgrdclr, their text to white, 
        //and their font to arial size 18
        captured1=new JLabel ("Player 1 has captured: ");
        captured1.setBackground(bckgrdclr);
        captured1.setForeground(Color.white);
        captured1.setFont (new Font("Arial", Font.PLAIN, 18));
        captured2=new JLabel ("Player 2 has captured: ");
        captured2.setBackground(bckgrdclr);
        captured2.setForeground(Color.white);
        captured2.setFont (new Font("Arial", Font.PLAIN, 18));
        //sets the layout of the two checker pieces in the player captured pieces area to border layout
        //so that the number of pieces captured can easily be added to them
        capturedIcon1.setLayout (new BorderLayout());
        capturedIcon2.setLayout (new BorderLayout());
        //creates the JLabels holding the number of pieces captured by each player, and
        //sets their font to arial size 18
        pcs1=new JLabel ("0", SwingConstants.CENTER);
        pcs1.setFont (new Font("Arial", Font.BOLD, 18));
        pcs2=new JLabel ("0", SwingConstants.CENTER);
        pcs2.setFont (new Font("Arial", Font.BOLD, 18));
        //creates the info JLabel, starting with text telling player 1 to move
        info=new JLabel ("Player 1, make your move!", SwingConstants.CENTER);
        info.setForeground (Color.white);
        info.setFont (new Font("Arial", Font.PLAIN, 14));
        //adds the number of pieces captured JLabel to the player piece JLabel
        capturedIcon1.add (pcs1, BorderLayout.CENTER);
        capturedIcon2.add (pcs2, BorderLayout.CENTER);
        //adds the pieces captured JLabels to the north JPanel
        north.add (captured1);
        north.add (capturedIcon1);
        north.add (captured2);
        north.add (capturedIcon2);
        //adds the info JLabel and main menu button to the south JPanel
        south.add (info);
        south.add (mainmenu);
        //first for loop will run 8 times (for the rows)
        //this will constitute all rows of 8 tiles
        for (int x=1;x<9;x++){
            //inner for loop will run 8 times every time outer loop runs once (for the columns)
            //this will constitute all the tiles and pieces in one row of the 8x8 board
            for (int y=1;y<9;y++){
                //(x*10+y) makes a single number with two digits that each represent the x (row) and y (column) value
                //of each tile on the board
                //this first check will run for all the tiles that should hold a player 2 piece
                if ((x*10+y)==12||(x*10+y)==14||(x*10+y)==16||(x*10+y)==18
                ||(x*10+y)==21||(x*10+y)==23||(x*10+y)==25||(x*10+y)==27
                ||(x*10+y)==32||(x*10+y)==34||(x*10+y)==36||(x*10+y)==38)
                {
                    //the player 2 piece is added to the tile, with a black background
                    //its actioncommand is set to the same number as (x*10+y), however,
                    //it is written differently since it is a String
                    //the numbers are not added, but instead simply put together (1+2 -> 12)
                    //additionally, their actionlisteners are set, their borders set to null,
                    //and their size set to 80x80 pixels to fit the 640x640 pixels board
                    btn[x][y]=new JButton(createImageIcon(Settings.colour2+".png"));
                    btn[x][y].addActionListener(this);
                    btn[x][y].setActionCommand(""+x+y);
                    btn[x][y].setBackground(Color.black);
                    btn[x][y].setBorder(null);
                    btn[x][y].setSize(80,80);
                    center.add(btn[x][y]);
                    //the value of these player 2 pieces is set to 2, and can be done with the
                    //same values of x and y since the two arrays are essentially mapped onto each other
                    btnval[x][y]=2;
                }
                //this check runs if the pieces to be added should be player 1 pieces 
                else if ((x*10+y)==61||(x*10+y)==63||(x*10+y)==65||(x*10+y)==67
                ||(x*10+y)==72||(x*10+y)==74||(x*10+y)==76||(x*10+y)==78
                ||(x*10+y)==81||(x*10+y)==83||(x*10+y)==85||(x*10+y)==87)
                {
                    //the player 1 pieces are added to their tiles with a black background,
                    //their actionlisteners are set, their borders set to null,
                    //and their size set to 80x80 pixels
                    btn[x][y]=new JButton(createImageIcon(Settings.colour1+".png"));
                    btn[x][y].addActionListener (this);
                    btn[x][y].setActionCommand (""+x+y);
                    btn[x][y].setBackground (Color.black);
                    btn[x][y].setBorder (null);
                    btn[x][y].setSize(80,80);
                    center.add(btn[x][y]);
                    //the player 1 pieces have their values set to 1
                    btnval[x][y]=1;
                }
                //this will run if the tile to be added should be black 
                else if ((x*10+y)==41||(x*10+y)==43||(x*10+y)==45||(x*10+y)==47
                ||(x*10+y)==52||(x*10+y)==54||(x*10+y)==56||(x*10+y)==58)
                {
                    //the black tiles are added, and their properties set the same way as the player pieces
                    btn[x][y]=new JButton();
                    btn[x][y].addActionListener(this);
                    btn[x][y].setActionCommand(""+x+y);
                    btn[x][y].setBackground(Color.black);
                    btn[x][y].setBorder(null);
                    btn[x][y].setSize(80,80);
                    center.add(btn[x][y]);
                    //the black tiles have their values set to 0
                    btnval[x][y]=0;
                }
                //runs when only the remaining tiles are left, to be set as white tiles
                else
                {
                    //the white tiles are added, with an actioncommand only used to tell the players
                    //that they cannot interact with white tiles
                    btn[x][y]=new JButton();
                    btn[x][y].addActionListener(this);
                    btn[x][y].setActionCommand("white");
                    btn[x][y].setBackground(Color.white);
                    btn[x][y].setBorder(null);
                    btn[x][y].setSize(80,80);
                    center.add(btn[x][y]);
                }
            }//end nested for
        }//end for
        //adds the panels to their respective location in BorderLayout on the background
        add (center, BorderLayout.CENTER);
        add (north, BorderLayout.NORTH);
        add (south, BorderLayout.SOUTH);
    }//end Checkers
    public void actionPerformed (ActionEvent e)
    {
        //first if statement checks if player has clicked a white tile, and tells them they cannot move there
        if (e.getActionCommand().equals ("white"))
        {
            info.setText ("You cannot move to white tiles.");
        }
        //checks if main menu button was clicked, and if so, takes player to main menu
        else if (e.getActionCommand().equals ("mainmenu"))
        {
            MainMenu object=new MainMenu();
            object.setVisible(true);
            object.setSize (1000, 900);
            this.setVisible(false);
        }
        //else statement will be true if the player has clicked a piece or black tile
        else 
        {
            //retrieves only the integers from the input actionCommand for their values to be checked
            positionInt=Integer.parseInt(e.getActionCommand());
            //finds both the x and y values of the position of the button whose actioncommand was called
            xval=((positionInt)/10);
            yval=((positionInt)-(10*((positionInt)/10)));
            //this if will run if it is player 1's turn
            if (turn==1)
            {
                //check if one of player 2's pieces was clicked, and tells players that it is player 1's turn,
                //and in case this was already known, tells the players that enemy pieces cannot be moved onto
                if ((btnval[xval][yval])==2||(btnval[xval][yval])==4)
                {info.setText ("It is player 1's turn. They cannot move on top of opponent pieces.");}
                //checks that the tile clicked had a player 1 piece on it
                if ((btnval[xval][yval])==1)
                {
                    //sets the piece to be moved as its coordinate value (e.g if x=6 and y=3 this value would be 63)
                    toMove=positionInt;
                }
                //checks if a black tile was clicked and a player piece was already chosen, and only runs if both are true
                //the second && check in this was added later, since this code ran when a king was moved as well (it still
                //fit the criteria of the if statement, as a black tile was clicked and a player piece was already chosen)
                //it checks if a normal player 1 piece was chosen
                if (((btnval[xval][yval])==0)&&(toMove!=0)&&((btnval[toMove/10][toMove-(10*(toMove/10))]==1)))
                {
                    //first check to see if the black tile clicked was a possible move
                    //(1 up and 1 left from the piece, or 1 up and 1 right from the piece, respectively)
                    if ((positionInt==toMove-11)||(positionInt==toMove-9))
                    {
                        //changes the tile to be moved onto to its coordinate value (e.g if x=6 and y=3 this value would be 63)
                        moveTo=positionInt;
                    }
                    //checks if a tile 2 up and 2 left from the clicked player piece was clicked
                    else if ((positionInt==toMove-22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval+1])==2||(btnval[xval+1][yval+1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs1 (pieces captured by player 1) value also increases by 1
                            (btnval[xval+1][yval+1])=0;
                            btn[xval+1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 1
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+".png"));
                            btnval[xval][yval]=1;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 up and 2 right from the clicked player piece was clicked
                    else if (positionInt==toMove-18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval-1])==2||(btnval[xval+1][yval-1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs1 (pieces captured by player 1) value also increases by 1
                            (btnval[xval+1][yval-1])=0;
                            btn[xval+1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 1
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+".png"));
                            btnval[xval][yval]=1;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //runs if any other black tile was clicked
                    else
                    //tells the player they cannot move to that black tile
                    {info.setText ("You cannot move there!");}
                }
                //this if statement will run when the player has chosen to move to an unoccupied tile
                if (moveTo!=0)
                {
                    //first the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                    //are changed to contain a black tile and its value of 0
                    xval=((toMove)/10);
                    yval=((toMove)-(10*((toMove)/10)));
                    btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                    btnval[xval][yval]=0;
                    //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                    //are changed to contain a player 1 piece and its value of 1
                    xval=((moveTo)/10);
                    yval=((moveTo)-(10*((moveTo)/10)));
                    btn[xval][yval].setIcon (createImageIcon(Settings.colour1+".png"));
                    btnval[xval][yval]=1;
                    //plays the piece moving sound effect if the user has kept sound effects on
                    if (Settings.soundLoop==1)
                    {   
                        Sound object=new Sound();
                        object.setVisible(false);
                    }
                    //resetting all the values relating to piece positions to prepare for the next turn
                    //also resets the info text, so as to not annoy the players or display incorrect information 
                    toMove=0;
                    moveTo=0;
                    xval=0;
                    yval=0;
                    turn=2;
                    info.setText ("");
                }
                //checks if a player 1 king piece was chosen
                if ((btnval[xval][yval])==3)
                {
                    toMove=positionInt;
                }
                if (((btnval[xval][yval])==0)&&(toMove!=0)&&((btnval[toMove/10][toMove-(10*(toMove/10))]==3)))
                {
                    //first check to see if the black tile clicked was a possible move
                    //(1 up/down and 1 right/left from the king piece)
                    if ((positionInt==toMove-11)||(positionInt==toMove-9)||(positionInt==toMove+9)||(positionInt==toMove+11))
                    {
                        //changes the tile to be moved onto to its coordinate value (e.g if x=6 and y=3 this value would be 63)
                        moveTo=positionInt;
                    }
                    //checks if a tile 2 up and 2 left from the clicked player piece was clicked
                    else if ((positionInt==toMove-22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval+1])==2||(btnval[xval+1][yval+1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs1 (pieces captured by player 1) value also increases by 1
                            (btnval[xval+1][yval+1])=0;
                            btn[xval+1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 3
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+"King.png"));
                            btnval[xval][yval]=3;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 up and 2 right from the clicked player piece was clicked
                    else if (positionInt==toMove-18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval-1])==2||(btnval[xval+1][yval-1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs1 (pieces captured by player 1) value also increases by 1
                            (btnval[xval+1][yval-1])=0;
                            btn[xval+1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 3
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+"King.png"));
                            btnval[xval][yval]=3;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    else if ((positionInt==toMove+22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval-1])==2||(btnval[xval-1][yval-1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval-1][yval-1])=0;
                            btn[xval-1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 3
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+"King.png"));
                            btnval[xval][yval]=3;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 down and 2 left from the clicked player piece was clicked
                    else if (positionInt==toMove+18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval+1])==2||(btnval[xval-1][yval+1])==4)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs1 (pieces captured by player 1) value also increases by 1
                            (btnval[xval-1][yval+1])=0;
                            btn[xval-1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum1++;
                            pcs1.setText (""+pcsNum1);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 2 piece and its value of 2
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour1+"King.png"));
                            btnval[xval][yval]=3;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=2;
                            info.setText ("");
                        }
                        //runs if any other black tile was clicked
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //this if statement will run when the player has chosen to move to an unoccupied tile
                    if ((moveTo!=0)&&(turn==1))
                    {
                        //first the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                        //are changed to contain a black tile and its value of 0
                        xval=((toMove)/10);
                        yval=((toMove)-(10*((toMove)/10)));
                        btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                        btnval[xval][yval]=0;
                        //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                        //are changed to contain a player 1 piece and its value of 1
                        xval=((moveTo)/10);
                        yval=((moveTo)-(10*((moveTo)/10)));
                        btn[xval][yval].setIcon (createImageIcon(Settings.colour1+"King.png"));
                        btnval[xval][yval]=3;
                        //plays the piece moving sound effect if the user has kept sound effects on
                        if (Settings.soundLoop==1)
                        {   
                            Sound object=new Sound();
                            object.setVisible(false);
                        }
                        //resetting all the values relating to piece positions to prepare for the next turn
                        //also resets the info text, so as to not annoy the players or display incorrect information 
                        toMove=0;
                        moveTo=0;
                        xval=0;
                        yval=0;
                        turn=2;
                        info.setText ("");
                    }
                }
            }
            else if (turn==2)
            {
                //check to see if one of player 1's pieces was clicked, and tells players that it is player 2's turn,
                //and in case this was already known, tells the players that enemy pieces cannot be moved onto
                if ((btnval[xval][yval])==1||(btnval[xval][yval])==3)
                {info.setText ("It is player 2's turn. They cannot move on top of opponent pieces.");}
                //checks that the tile clicked had a player 2 piece on it
                if ((btnval[xval][yval])==2)
                {
                    //sets the piece to be moved as its coordinate value (e.g if x=6 and y=3 this value would be 63)
                    toMove=positionInt;
                }
                //checks if a black tile was clicked and a player piece was already chosen, and only runs if both are true
                //the second && check in this was added later, since this code ran when a king was chosen as well (it still
                //fit the criteria of the if statement, as a black tile was clicked and a player piece was already chosen)
                //it checks if a normal player 2 piece was chosen
                if (((btnval[xval][yval])==0)&&(toMove!=0)&&((btnval[toMove/10][toMove-(10*(toMove/10))]==2)))
                {
                    //first check to see if the black tile clicked was a possible move
                    //(1 down and 1 right from the piece, or 1 down and 1 left from the piece, respectively)
                    if ((positionInt==toMove+11)||(positionInt==toMove+9))
                    {
                        //changes the tile to be moved onto to its coordinate value
                        moveTo=positionInt;
                    }
                    //checks if a tile 2 down and 2 right from the clicked player piece was clicked
                    else if ((positionInt==toMove+22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval-1])==1||(btnval[xval-1][yval-1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval-1][yval-1])=0;
                            btn[xval-1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 1
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+".png"));
                            btnval[xval][yval]=2;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information 
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 down and 2 right from the clicked player piece was clicked
                    else if (positionInt==toMove+18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval+1])==1||(btnval[xval-1][yval+1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval-1][yval+1])=0;
                            btn[xval-1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 2 piece and its value of 2
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+".png"));
                            btnval[xval][yval]=2;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //runs if any other black tile was clicked
                    else
                    //tells the player they cannot move to that black tile
                    {info.setText ("You cannot move there!");}
                }
                //this if statement will run when the player has chosen to move to an unoccupied tile
                if (moveTo!=0)
                {
                    //first the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                    //are changed to contain a black tile and its value of 0
                    xval=((toMove)/10);
                    yval=((toMove)-(10*((toMove)/10)));
                    btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                    btnval[xval][yval]=0;
                    //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                    //are changed to contain a player 1 piece and its value of 1
                    xval=((moveTo)/10);
                    yval=((moveTo)-(10*((moveTo)/10)));
                    btn[xval][yval].setIcon (createImageIcon(Settings.colour2+".png"));
                    btnval[xval][yval]=2;
                    //plays the piece moving sound effect if the user has kept sound effects on
                    if (Settings.soundLoop==1)
                    {   
                        Sound object=new Sound();
                        object.setVisible(false);
                    }
                    //resetting all the values relating to piece positions to prepare for the next turn
                    //also resets the info text, so as to not annoy the players or display incorrect information
                    toMove=0;
                    moveTo=0;
                    xval=0;
                    yval=0;
                    turn=1;
                    info.setText ("");
                }
                //checks if a king piece was chosen
                if ((btnval[xval][yval])==4)
                {
                    toMove=positionInt;
                }
                if (((btnval[xval][yval])==0)&&(toMove!=0)&&((btnval[toMove/10][toMove-(10*(toMove/10))]==4)))
                {
                    //first checks if the black tile clicked was a possible move
                    //(1 up/down and 1 right/left from the king piece)
                    if ((positionInt==toMove-11)||(positionInt==toMove-9)||(positionInt==toMove+9)||(positionInt==toMove+11))
                    {
                        //changes the tile to be moved onto to its coordinate value (e.g if x=6 and y=3 this value would be 63)
                        moveTo=positionInt;
                    }
                    //checks if a tile 2 up and 2 left from the clicked player piece was clicked
                    else if ((positionInt==toMove-22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval+1])==1||(btnval[xval+1][yval+1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval+1][yval+1])=0;
                            btn[xval+1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 2 piece and its value of 4
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+"King.png"));
                            btnval[xval][yval]=4;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 up and 2 right from the clicked player piece was clicked
                    else if (positionInt==toMove-18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 up and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval+1][yval-1])==1||(btnval[xval+1][yval-1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval+1][yval-1])=0;
                            btn[xval+1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 1 piece and its value of 1
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+"King.png"));
                            btnval[xval][yval]=4;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    else if ((positionInt==toMove+22))
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 right from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval-1])==1||(btnval[xval-1][yval-1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval-1][yval-1])=0;
                            btn[xval-1][yval-1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 2 piece and its value of 4
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+"King.png"));
                            btnval[xval][yval]=4;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if the tile between the player and black tile does not contain an opponent piece
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //checks if a tile 2 down and 2 left from the clicked player piece was clicked
                    else if (positionInt==toMove+18)
                    {
                        //checks if the tile between the player piece and clicked black tile is occupied by an opponent piece
                        //(if the tile 1 down and 1 left from the player piece contains an opponent piece)
                        if ((btnval[xval-1][yval+1])==1||(btnval[xval-1][yval+1])==3)
                        {
                            //first the tile between the player piece and previously black tile is overwritten to contain
                            //a black tile and its value of 0
                            //the pcs2 (pieces captured by player 2) value also increases by 1
                            (btnval[xval-1][yval+1])=0;
                            btn[xval-1][yval+1].setIcon (createImageIcon("nothing.png"));
                            pcsNum2++;
                            pcs2.setText (""+pcsNum2);

                            //now the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                            //are changed to contain a black tile and its value of 0
                            xval=((toMove)/10);
                            yval=((toMove)-(10*((toMove)/10)));
                            btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                            btnval[xval][yval]=0;
                            //changes the tile to be moved onto to its coordinate value
                            moveTo=positionInt;
                            //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                            //are changed to contain a player 2 piece and its value of 4
                            xval=((moveTo)/10);
                            yval=((moveTo)-(10*((moveTo)/10)));
                            btn[xval][yval].setIcon (createImageIcon(Settings.colour2+"King.png"));
                            btnval[xval][yval]=4;
                            //plays the piece moving sound effect if the user has kept sound effects on
                            if (Settings.soundLoop==1)
                            {   
                                Sound object=new Sound();
                                object.setVisible(false);
                            }
                            //resetting all the values relating to piece positions to prepare for the next turn
                            //also resets the info text, so as to not annoy the players or display incorrect information
                            toMove=0;
                            moveTo=0;
                            xval=0;
                            yval=0;
                            turn=1;
                            info.setText ("");
                        }
                        //runs if any other black tile was clicked
                        else
                        //tells the player they cannot move to that black tile
                        {info.setText ("You cannot move there!");}
                    }
                    //this if statement will run when the player has chosen to move to an unoccupied tile
                    if ((moveTo!=0)&&(turn==2))
                    {
                        //first the x and y values of the player piece are retrieved from toMove, then the btn and btn val
                        //are changed to contain a black tile and its value of 0
                        xval=((toMove)/10);
                        yval=((toMove)-(10*((toMove)/10)));
                        btn[xval][yval].setIcon (createImageIcon("nothing.png"));
                        btnval[xval][yval]=0;
                        //now the x and y values of the black tile are retrieved from moveTo, then the btn and btn val
                        //are changed to contain a player 1 piece and its value of 1
                        xval=((moveTo)/10);
                        yval=((moveTo)-(10*((moveTo)/10)));
                        btn[xval][yval].setIcon (createImageIcon(Settings.colour2+"King.png"));
                        btnval[xval][yval]=4;
                        //plays the piece moving sound effect if the user has kept sound effects on
                        if (Settings.soundLoop==1)
                        {   
                            Sound object=new Sound();
                            object.setVisible(false);
                        }
                        //resetting all the values relating to piece positions to prepare for the next turn
                        //also resets the info text, so as to not annoy the players or display incorrect information
                        toMove=0;
                        moveTo=0;
                        xval=0;
                        yval=0;
                        turn=1;
                        info.setText ("");
                    }
                }
            }
        }
        //first check to make pieces kings, this loop runs to check the top row of tiles for player 1 pieces
        for (int a=2;a<9;a+=2)
        {
            //checks 1 tile at a time
            if (btnval[1][a]==1)
            {
                //if player 1 pieces are found, their icon and btnval are set to a king icon and val of 3, respectively
                btn[1][a].setIcon (createImageIcon(Settings.colour1+"King.png"));
                btnval[1][a]=3;
            }
        }
        //check to make player 2 pieces kings, this loop runs to check the bottom row of tiles for player 2 pieces
        for (int b=1;b<9;b+=2)
        {
            //checks 1 tile at a time
            if (btnval[8][b]==2)
            {
                //if player 2 pieces are found, their icon and btnval are set to a king icon and val of 4, respectively
                btn[8][b].setIcon (createImageIcon(Settings.colour2+"King.png"));
                btnval[8][b]=4;
            }
        }
        //checks if all player 2 pieces have been captured by player 1
        if (pcsNum1==12)
        {
            //sets the winner String to its correct text, to be displayed in class WinMenu
            winner=("Player 1 has won!");
            WinMenu object=new WinMenu();
            object.setVisible(true);
            object.setSize (1000, 575);
            this.setVisible(false);
        }
        //checks if all player 1 pieces have been captured by player 2
        else if (pcsNum2==12)
        {
            //sets the winner String to its correct text, to be displayed in class WinMenu
            winner=("Player 2 has won!");
            WinMenu object=new WinMenu();
            object.setVisible(true);
            object.setSize (1000, 575);
            this.setVisible(false);
        }
    }//end actionPerformed

    //fetches images when they are called in the program, and returns the message
    //"Couldn't find the file: (path)" when the image does not exist in the program/specified folder
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL=Checkers.class.getResource (path);
        if (imgURL !=null)
        {return new ImageIcon (imgURL);}
        else
        {System.err.println ("Couldn't find the file: "+path);
            return null;}
    }//end createImageIcon
}
