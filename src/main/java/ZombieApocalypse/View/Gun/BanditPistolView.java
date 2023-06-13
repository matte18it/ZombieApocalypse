package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BanditPistolView {
    private final GunAnimation gunAnimation;
    public Image currentImage;


    public BanditPistolView() {
        gunAnimation= new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL);
            currentImage=gunAnimation.getDefaultImage();


    }

    public void update(Enemy e)  {
            switch (e.dir){
                case LEFT ->  currentImage=gunAnimation.update(0);
                case UP -> currentImage=gunAnimation.update(270);
                case RIGHT -> currentImage=gunAnimation.update(180);
                case DOWN -> currentImage=gunAnimation.update(90);
            }


    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
