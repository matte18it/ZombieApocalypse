package ZombieApocalypse.Loop;

import ZombieApocalypse.View.LoginView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginLoop {
    LoginView view;
    private ScheduledExecutorService executor;
    public LoginLoop(LoginView view){
        this.view = view;
    }

    public void start(){
        //Game loop con un thread che agisce ogni 60 millisecondi chiamando la funzione updatePosition che serve per l'animazione del titolo
        if (executor != null)
            return;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> view.updatePosition(),
                0, 60, TimeUnit.MILLISECONDS);
    }

    public void stop(){
        executor.shutdown();
    }
}
