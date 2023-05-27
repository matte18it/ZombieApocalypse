package ZombieApocalypse.Utility;

import ZombieApocalypse.ResourcesLoader;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlayMusic {
    private static Clip clip;
    private static PlayMusic  play= null;

    private PlayMusic(){}
    public static PlayMusic getInstance(){
        if(play == null)
            play = new PlayMusic();
        return play;
    }
    public void play(String path){
        clip = ResourcesLoader.getInstance().getAudioClip(path);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){ clip.stop(); }
    public boolean isMusic(){
        if(clip != null)
            return clip.isRunning();
        return false;
    }

}
