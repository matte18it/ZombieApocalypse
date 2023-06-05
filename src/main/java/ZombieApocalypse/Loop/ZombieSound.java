package ZombieApocalypse.Loop;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ZombieSound {
    private ScheduledExecutorService executor;
    public void start(){
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> soundZombie(), 0, new Random().nextInt(0, 60), TimeUnit.SECONDS);
    }

    private void soundZombie() {
        if(GameData.sound) {
            PlayWav.getInstance().loadSound("/Audio/ZombieSound.wav");
            PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
            PlayWav.getInstance().playSound();
        }
    }

    public void stop(){
        executor.shutdown();
    }
}
