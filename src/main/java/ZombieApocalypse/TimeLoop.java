package ZombieApocalypse;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.View.MenuBarView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLoop {

    private static ScheduledExecutorService executor;


    public static void start() {
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> MenuBarView.updateTime(),
                0, 1000, TimeUnit.MILLISECONDS);

    }
    public static void stop(){
        executor.shutdown();
    }
}
