package ZombieApocalypse.Utility;

import ZombieApocalypse.Model.Game;

import javax.sound.sampled.*;

public class PlayWav {
    //Classe singleton per gestione musica e suoni
    private static Clip clip = null;        //variabile per la musica
    private static FloatControl music;      //controllore utile per il volume

    //clip per i suoni
    private static Clip clipZombie = null, clipAmmo = null, clipButton = null, clipGameOver = null, walkRoad = null;
    private static Clip clipGranata = null,  clipHurt1 = null, clipHurt2 = null, clipHurt3 = null;
    private static Clip clipKnife = null, clipMedikit = null, clipPotion = null, clipPump = null;
    private static Clip clipPistola = null, clipWak = null,   clipZombieHit = null;
    private static Clip clipYouWin = null, clipMission = null;
    private static FloatControl sound;  //controllore dei suoni

    private static PlayWav play = null;

    private PlayWav(){}
    public static PlayWav getInstance(){
        if(play == null)
            play = new PlayWav();
        return play;
    }

    //----- Musica -----
    public void play(String path){
        //carico la musica, la avvio e la setto in loop. Infine prendo il controllore della musica
        clip = ResourcesLoader.getInstance().getAudioClip(path);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        music = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    public void stop(){
        //stoppo la musica
        clip.stop();
    }
    public boolean isPlay(){
        //restituisco se la musica è avviata oppure no
        if(clip != null)
            return clip.isRunning();
        return false;
    }
    public void setVolume(int value){
        //setto volume della musica
        music.setValue(value);
    }

    //----- Suoni -----
    public void playGameOverSound(){
        //se la clip è vuota, la creo. Poi prendo il controllore del suono, setto il volume e avvio. Se non è nulla la faccio ripartire da capo
        //Faccio questo per ogni clip
        if(clipGameOver == null)
            clipGameOver = ResourcesLoader.getInstance().getAudioClip("/Audio/YouLose.wav");
        else if(clipGameOver.getFramePosition() != 0)
            clipGameOver.setFramePosition(0);

        sound = (FloatControl) clipGameOver.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipGameOver.start();
    }
    public void playZombie(){
        if(clipZombie == null)
            clipZombie = ResourcesLoader.getInstance().getAudioClip("/Audio/ZombieSound.wav");
        else if(clipZombie.getFramePosition() != 0)
            clipZombie.setFramePosition(0);

        sound = (FloatControl) clipZombie.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipZombie.start();
    }

    public void playAmmoSound(){
        //in alcune clip che mi servono a ripetizione le carico ogni volta
        clipAmmo = ResourcesLoader.getInstance().getAudioClip("/Audio/AmmoSound.wav");
        sound = (FloatControl) clipAmmo.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipAmmo.start();
    }

    public void playButtonSound(){
        clipButton = ResourcesLoader.getInstance().getAudioClip("/Audio/ButtonSound.wav");
        sound = (FloatControl) clipButton.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipButton.start();
    }

    public void playGrenadeSound(){
        clipGranata = ResourcesLoader.getInstance().getAudioClip("/Audio/Grenade.wav");
        sound = (FloatControl) clipGranata.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipGranata.start();
    }

    public void playHurt1Sound(){
        if(clipHurt1 == null)
            clipHurt1 = ResourcesLoader.getInstance().getAudioClip("/Audio/Hurt1.wav");
        else if(clipHurt1.getFramePosition() != 0)
            clipHurt1.setFramePosition(0);

        sound = (FloatControl) clipHurt1.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipHurt1.start();
    }

    public void playHurt2Sound(){
        if(clipHurt2 == null)
            clipHurt2 = ResourcesLoader.getInstance().getAudioClip("/Audio/Hurt2.wav");
        else if(clipHurt2.getFramePosition() != 0)
            clipHurt2.setFramePosition(0);

        sound = (FloatControl) clipHurt2.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipHurt2.start();
    }

    public void playHurt3Sound(){
        if(clipHurt3 == null)
            clipHurt3 = ResourcesLoader.getInstance().getAudioClip("/Audio/Hurt3.wav");
        else if(clipHurt3.getFramePosition() != 0)
            clipHurt3.setFramePosition(0);

        sound = (FloatControl) clipHurt3.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipHurt3.start();
    }

    public void playKnifeSound(){
        clipKnife = ResourcesLoader.getInstance().getAudioClip("/Audio/KnifeSound.wav");
        sound = (FloatControl) clipKnife.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipKnife.start();
    }

    public void playMedikitSound(){
        if(clipMedikit == null)
            clipMedikit = ResourcesLoader.getInstance().getAudioClip("/Audio/MedikitSound.wav");
        else if(clipMedikit.getFramePosition() != 0)
            clipMedikit.setFramePosition(0);

        sound = (FloatControl) clipMedikit.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipMedikit.start();
    }

    public void playPotionSound(){
        if(clipPotion == null)
            clipPotion = ResourcesLoader.getInstance().getAudioClip("/Audio/PotionDrink.wav");
        else if(clipPotion.getFramePosition() != 0)
            clipPotion.setFramePosition(0);

        sound = (FloatControl) clipPotion.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipPotion.start();
    }

    public void playPumpShot(){
        if(clipPump == null)
            clipPump = ResourcesLoader.getInstance().getAudioClip("/Audio/PumpShot.wav");
        else if(clipPump.getFramePosition() != 0)
            clipPump.setFramePosition(0);

        sound = (FloatControl) clipPump.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipPump.start();
    }


    public void playShotPistola(){
        if(clipPistola==null)
        clipPistola = ResourcesLoader.getInstance().getAudioClip("/Audio/ShotPistola.wav");
        else if(clipPistola.getFramePosition() != 0)
            clipPistola.setFramePosition(0);
        sound = (FloatControl) clipPistola.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipPistola.start();
    }

    public void playWalkSound(int x, int y){
        if(Game.getInstance().getWorld().isRoad(x,y)){
            walkRoad = ResourcesLoader.getInstance().getAudioClip("/Audio/WalkRoad.wav");
            sound = (FloatControl) walkRoad.getControl(FloatControl.Type.MASTER_GAIN);
            sound.setValue(GameData.soundVolume);
            walkRoad.start();}else{
        clipWak = ResourcesLoader.getInstance().getAudioClip("/Audio/WalkSound.wav");
        sound = (FloatControl) clipWak.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipWak.start();}
    }

    public void playZombieHit(){
        clipZombieHit = ResourcesLoader.getInstance().getAudioClip("/Audio/ZombieHit.wav");


        sound = (FloatControl) clipZombieHit.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipZombieHit.start();
    }

    public void playYouWin(){
        if(clipYouWin == null)
            clipYouWin = ResourcesLoader.getInstance().getAudioClip("/Audio/YouWin.wav");
        else if(clipYouWin.getFramePosition() != 0)
            clipYouWin.setFramePosition(0);

        sound = (FloatControl) clipYouWin.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipYouWin.start();
    }

    public void playMissionComplete() {
        if(clipMission == null)
            clipMission = ResourcesLoader.getInstance().getAudioClip("/Audio/MissionComplete.wav");
        else if(clipMission.getFramePosition() != 0)
            clipMission.setFramePosition(0);

        sound = (FloatControl) clipMission.getControl(FloatControl.Type.MASTER_GAIN);
        sound.setValue(GameData.soundVolume);
        clipMission.start();
    }
}