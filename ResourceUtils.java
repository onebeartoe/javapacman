import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.net.URL;

public class ResourceUtils {
    static Clip loadSound(String name){
        try{
            URL url = ResourceUtils.class.getResource(name);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        }catch(Exception e){}
        return null;
    }

    static Image loadImage(String name){
        return Toolkit.getDefaultToolkit().getImage(name);
    }
}
