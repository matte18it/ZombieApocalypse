package ZombieApocalypse.Utility;

import ZombieApocalypse.ResourcesLoader;

import javax.sound.sampled.Clip;

public class PlayWav {
    private static Clip clip = null;
    private static PlayWav play = null;

    private PlayWav(){}
    public static PlayWav getInstance(){
        if(play == null)
            play = new PlayWav();
        return play;
    }
    public void play(String path){
        clip = ResourcesLoader.getInstance().getAudioClip(path);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){ clip.stop(); }
    public boolean isPlay(){
        if(clip != null)
            return clip.isRunning();
        return false;
    }

}
