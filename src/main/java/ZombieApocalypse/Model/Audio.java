package ZombieApocalypse.Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Audio {
    Clip clip;
    // Salvare paths dei file audio
    URL audioURL[] = new URL[30];

    public Audio() {
        audioURL[0] = getClass().getResource("/Audio/walk_2.wav");
        audioURL[1] = getClass().getResource("/Audio/hit.wav");
        audioURL[2] = getClass().getResource("/Audio/hurt.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){

        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
