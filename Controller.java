import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller  implements MouseListener, KeyListener {
    private Board board;


    public void bind(Board board){
        this.board = board;
        board.addMouseListener(this);
        board.addKeyListener(this);

    }

    /* Handles user key presses*/
    public void keyPressed(KeyEvent e)
    {
        /* Pressing a key in the title screen starts a game */
        if (board.titleScreen)
        {
            board.titleScreen = false;
            return;
        }
        /* Pressing a key in the win screen or game over screen goes to the title screen */
        else if (board.winScreen || board.overScreen)
        {
            board.titleScreen = true;
            board.winScreen = false;
            board.overScreen = false;
            return;
        }
        /* Pressing a key during a demo kills the demo mode and starts a new game */
        else if (board.demo)
        {
            board.demo=false;
            /* Stop any pacman eating sounds */
            board.sounds.nomNomStop();
            board.New=1;
            return;
        }

        /* Otherwise, key presses control the player! */
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                board.player.desiredDirection='L';
                break;
            case KeyEvent.VK_RIGHT:
                board.player.desiredDirection='R';
                break;
            case KeyEvent.VK_UP:
                board.player.desiredDirection='U';
                break;
            case KeyEvent.VK_DOWN:
                board.player.desiredDirection='D';
                break;
        }

        board.repaintBoard();
    }

    /* This function detects user clicks on the menu items on the bottom of the screen */
    public void mousePressed(MouseEvent e){
        if (board.titleScreen || board.winScreen || board.overScreen)
        {
            /* If we aren't in the game where a menu is showing, ignore clicks */
            return;
        }

        /* Get coordinates of click */
        int x = e.getX();
        int y = e.getY();
        if ( 400 <= y && y <= 460)
        {
            if ( 100 <= x && x <= 150)
            {
                /* New game has been clicked */
                board.New = 1;
            }
            else if (180 <= x && x <= 300)
            {
                /* Clear high scores has been clicked */
                board.score.clearHighScores();
            }
            else if (350 <= x && x <= 420)
            {
                /* Exit has been clicked */
                System.exit(0);
            }
        }
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}
