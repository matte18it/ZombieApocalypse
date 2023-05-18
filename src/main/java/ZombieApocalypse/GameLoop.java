package ZombieApocalypse;

import ZombieApocalypse.Controller.PlayerController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop {

    private final PlayerController controller;
    private ScheduledExecutorService executor;
    public GameLoop(PlayerController controller) {
        this.controller = controller;
    }

    public void start() {
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> controller.update(),
                0, 60, TimeUnit.MILLISECONDS);
    }
}