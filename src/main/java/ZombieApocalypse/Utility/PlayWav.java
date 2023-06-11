package ZombieApocalypse.Utility;

import javax.sound.sampled.*;

public class PlayWav {
    private static Clip clip = null;
    private static FloatControl music;

    private static Clip clipSound = null;
    private static FloatControl sound;

    private static PlayWav play = null;

    private PlayWav(){}
    public static PlayWav getInstance(){
        if(play == null)
            play = new PlayWav();
        return play;
    }

    //----- Musica -----
    public void play(String path){
        clip = ResourcesLoader.getInstance().getAudioClip(path);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        music = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    public void stop(){ clip.stop(); }
    public boolean isPlay(){
        if(clip != null)
            return clip.isRunning();
        return false;
    }
    public void setVolume(int value){
        music.setValue(value);
    }

    //----- Suoni -----
    public void playZombie(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/ZombieSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playAmmoSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/AmmoSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playButtonSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/ButtonSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playGameOverSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/YouLose.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playGrenadeSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/Grenade.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playHurtSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/Hurt.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playKnifeSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/KnifeSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playMedikitSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/MedikitSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playPotionSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/PotionDrink.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playPumpShot(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/PumpShot.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playRadioSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/RadioSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playShotPistola(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/ShotPistola.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playWalkSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/WalkSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playWinSound(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/WinSound.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playZombieDeath(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/ZombieDeath.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playZombieHit(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/ZombieHit.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

    public void playYouWin(){
        clipSound = ResourcesLoader.getInstance().getAudioClip("/Audio/YouWin.wav");
        sound = (FloatControl) clipSound.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipSound.start();
    }

}