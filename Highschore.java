import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Highschore {
    int currScore;
    int highScore;

    public Highschore(){
        initHighScores();
        currScore=0;
        highScore=0;
    }

    /* if the high scores have been cleared, we have to update the top of the screen to reflect that */
    boolean clearHighScores= false;

    /* Reads the high scores file and saves it */
    public void initHighScores()
    {
        File file = new File("highScores.txt");
        Scanner sc;
        try
        {
            sc = new Scanner(file);
            highScore = sc.nextInt();
            sc.close();
        }
        catch(Exception e)
        {
        }
    }


    /* Writes the new high score to a file and sets flag to update it on screen */
    public void updateScore(int score)
    {
        PrintWriter out;
        try
        {
            out = new PrintWriter("highScores.txt");
            out.println(score);
            out.close();
        }
        catch(Exception e)
        {
        }
        highScore=score;
        clearHighScores=true;
    }

    /* Wipes the high scores file and sets flag to update it on screen */
    public void clearHighScores()
    {
        PrintWriter out;
        try
        {
            out = new PrintWriter("highScores.txt");
            out.println("0");
            out.close();
        }
        catch(Exception e)
        {
        }
        highScore=0;
        clearHighScores=true;
    }
}
