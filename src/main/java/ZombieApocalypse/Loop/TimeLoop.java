package ZombieApocalypse.Loop;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.Model.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLoop {

    private  ScheduledExecutorService executor;
    public static long time=0;



    public  void start() {
        time=System.nanoTime();
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> Game.getInstance().updateTime(time),
                0, 1000, TimeUnit.MILLISECONDS);

    }
    public  void stop(){
        executor.shutdown();
    }

}
