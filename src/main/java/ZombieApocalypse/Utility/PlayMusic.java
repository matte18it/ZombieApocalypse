package ZombieApocalypse.Utility;

import ZombieApocalypse.ResourcesLoader;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlayMusic {
    private static Clip clip;

    public PlayMusic(String path){
        clip = ResourcesLoader.getInstance().getAudioClip(path);
    }
    public void playMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopMusic(){ clip.stop(); }
    public boolean isMusic(){ return clip.isRunning(); }
    public void changeMusic(String path){
        clip = ResourcesLoader.getInstance().getAudioClip(path);
    }

}
