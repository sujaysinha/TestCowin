import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlay extends Thread
{
    Long currentFrame;


    // current status of clip
    String status;


    static String filePath;
    public void  run()
    {
        try
        {
            filePath = "D:\\beep.wav";


            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            for(int i=0;i<3;i++)
            {
                AudioInputStream audioInputStream =
                        AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

                // create clip reference
                Clip clip = AudioSystem.getClip();

                // open audioInputStream to the clip
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(2000);
            }
        }
        catch(Exception e)
        {
      //      System.out.println(e.getMessage());
        }
    }
}
