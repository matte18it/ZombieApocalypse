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

    public  ExecutorService getExecutor() {
        return executor;
    }

    public Image get(){
        try{
                return future.get();
        }catch (CancellationException |ExecutionException | InterruptedException e){
            ResultsPanel.getInstance().showError("Cancellazione, Interruzione o Errore nell'esecuzione di una task nella ThreadPool intregata ->"+ e.getMessage(), 80, e);
        }
        return null;
    }
    public static Future<CharacterAnimation> executeCharacterAnimation(CharacterAnimation task) {
        return  executor.submit(()->task);
    }

    public static Future<ItemAnimation> executeItemAnimation(ItemAnimation task) {
        return  executor.submit(()->task);
    }
    public static Future<GunAttackAnimation> GunAttackAnimation(GunAttackAnimation task) {
        return  executor.submit(()->task);
    }




    public static void stop(){
            executor.shutdown();
        }

}
