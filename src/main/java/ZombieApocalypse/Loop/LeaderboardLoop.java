package ZombieApocalypse.Loop;

import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeaderboardLoop {
    MenuView view;
    private ScheduledExecutorService executor;
    public LeaderboardLoop(MenuView view){
        this.view = view;
    }

    public void start(){
        //Game loop con un thread che agisce ogni 60 millisecondi chiamando la funzione updatePosition che serve per l'animazione del titolo
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
                    try { view.updateLeaderboard(); } catch (MalformedURLException e) { throw new RuntimeException(e); } catch (IOException e) { throw new RuntimeException(e);}
                },
                0, 1, TimeUnit.MINUTES);
    }

    public void stop(){
        executor.shutdown();
    }
}
