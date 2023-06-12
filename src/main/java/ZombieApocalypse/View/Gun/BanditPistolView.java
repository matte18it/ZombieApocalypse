package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BanditPistolView {
    private final Future<GunAnimation> gunAnimation;
    public Image currentImage;


    public BanditPistolView() {
        gunAnimation= ThreadPool.executeGunAnimation(new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL));
        try {
            currentImage=gunAnimation.get().getDefaultImage();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }

    }

    public void update(Enemy e)  {
        try {
            switch (e.dir){
                case LEFT ->  currentImage=gunAnimation.get().update(0);
                case UP -> currentImage=gunAnimation.get().update(270);
                case RIGHT -> currentImage=gunAnimation.get().update(180);
                case DOWN -> currentImage=gunAnimation.get().update(90);
            }
        }catch (ExecutionException | InterruptedException h){
            h.printStackTrace();
            System.exit(207);
        }

    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
