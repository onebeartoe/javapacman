/* Drew Schuster */
import java.net.URL;
import javax.sound.sampled.*;


/* This class controls all sound effects*/
public class GameSounds{
    
    Clip nomNom;
    Clip newGame;
    Clip death;
    /* Keeps track of whether or not the eating sound is playing*/
    boolean stopped;

    Clip loadSound(String name){
        try{
            URL url = this.getClass().getClassLoader().getResource(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        }catch(Exception e){}
        return null;
    }

/* Initialize audio files */ 
    public GameSounds(){
        stopped=true;
        // Pacman eating sound
        nomNom = loadSound("sounds/nomnom.wav");
        // newGame
        newGame = loadSound("sounds/newGame.wav");
        // death
        death = loadSound("sounds/death.wav");
    }
    
    /* Play pacman eating sound */
    public void nomNom(){
        /* If it's already playing, don't start it playing again!*/
        if (!stopped)
          return;

        stopped=false;
        nomNom.stop();
        nomNom.setFramePosition(0);
        nomNom.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /* Stop pacman eating sound */
    public void nomNomStop(){
        stopped=true;
        nomNom.stop();
        nomNom.setFramePosition(0);
    }
    
    /* Play new game sound */
    public void newGame(){
        newGame.stop();
        newGame.setFramePosition(0);
        newGame.start();
    }
    
    /* Play pacman death sound */
    public void death(){
        death.stop();
        death.setFramePosition(0);
        death.start();
    }
}
