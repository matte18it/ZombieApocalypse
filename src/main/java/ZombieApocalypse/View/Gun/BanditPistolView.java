package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Enemy.Enemy;

import java.awt.*;

public class BanditPistolView {
    private final GunAnimation gunAnimation;
    public Image currentImage;


    public BanditPistolView() {
        gunAnimation= new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Enemy e) {
        switch (e.dir){
            case RIGHT ->  currentImage=gunAnimation.update(0);
            case DOWN -> currentImage=gunAnimation.update(270);
            case LEFT -> currentImage=gunAnimation.update(180);
            case UP -> currentImage=gunAnimation.update(90);
        }
    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
