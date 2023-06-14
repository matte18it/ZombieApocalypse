package ZombieApocalypse.Utility;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.View.CharacterAnimation;
import ZombieApocalypse.View.GraphicPanel;
import ZombieApocalypse.View.Gun.GunAnimation;
import ZombieApocalypse.View.Gun.GunAttackAnimation;
import ZombieApocalypse.View.Gun.ItemAnimation;

import java.awt.*;
import java.lang.reflect.Executable;
import java.util.Objects;
import java.util.concurrent.*;

public class ThreadPool {
    Future<Image> future;
    private static final ExecutorService executor=Executors.newCachedThreadPool();;
    public ThreadPool(Image task) {
        future=executor.submit(()->task);
    }
    public ThreadPool(){};

    public Image get(){
        try{
                return future.get();
        }catch (CancellationException |ExecutionException | InterruptedException e){
            ResultsPanel.getInstance().showError("Cancellazione, Interruzione o Errore nel caricamento di una risorsa tramite ThreadPool ->"+ e.getMessage(), 80, e);
        }
        return null;
    }


    public static void stop(){
            executor.shutdown();
        }

}
