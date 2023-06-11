package ZombieApocalypse.Loop;

import ZombieApocalypse.Controller.PlayerController;

import java.util.concurrent.*;

public class GameLoop {

    private final PlayerController controller;
    private ScheduledExecutorService executor;
    public GameLoop(PlayerController controller) {
        this.controller = controller;
    }

    public void start() {
        if(executor==null)
            executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(this.controller::update,
                    0L, 60L, TimeUnit.MILLISECONDS);


        }



    public void stop(){
        executor.shutdown();
    }
}