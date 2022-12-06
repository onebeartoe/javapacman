/* Drew Schuster */
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.JApplet;
import java.awt.*;
import java.util.*;
import java.lang.*;

/* This class contains the entire game... most of the game logic is in the Board class but this
   creates the gui and captures mouse and keyboard input, as well as controls the game states */
public class Pacman extends JApplet
{ 

  /* These timers are used to kill title, game over, and victory screens after a set idle period (5 seconds)*/


  /* Create a new board */
  Board b=new Board();
  Controller controller = new Controller();

  /* This timer is used to do request new frames be drawn*/
  javax.swing.Timer frameTimer;
 

  /* This constructor creates the entire game essentially */   
  public Pacman()
  {

    b.requestFocus();
    /* Create and set up window frame*/
    JFrame f=new JFrame(); 
    f.setSize(420,460);

    /* Add the board to the frame */
    f.add(b,BorderLayout.CENTER);

    /*Set listeners for mouse actions and button clicks*/


    /* Make frame visible, disable resizing */
    f.setVisible(true);
    f.setResizable(false);

    /* Set the New flag to 1 because this is a new game */
    b.New=1;

    /* Manually call the first frameStep to initialize the game. */
    b.stepFrame(true,frameTimer);

    controller.bind(b);
    /* Create a timer that calls stepFrame every 30 milliseconds */
    frameTimer = new javax.swing.Timer(30, e -> b.stepFrame(false,frameTimer));

    /* Start the timer */
    frameTimer.start();

    b.requestFocus();
  }

  /* This repaint function repaints only the parts of the screen that may have changed.
     Namely the area around every player ghost and the menu bars
  */







  
  /* Main function simply creates a new pacman instance*/
  public static void main(String [] args)
  {
      Pacman c = new Pacman();
  } 
}
