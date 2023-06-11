package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemy;

import java.awt.*;
import java.util.concurrent.ExecutionException;

public interface EnemyViewInterface {

    void update() throws ExecutionException, InterruptedException;
    default void getUpdate(){
        try{
            update();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Errore nel caricamento delle Immagini dei nemici");
            System.exit(207);
        }
    }

    Image getCurrentImage();

}
