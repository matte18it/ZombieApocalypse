package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Loop.TimeLoop;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GunView {
    private final GunAnimation gunAnimation;
    private final GunAttackAnimation attackFrame;
    public Image currentImage;
    public Point imagePosition;

    public GunView() {
        gunAnimation= new GunAnimation("Coltello",4);
        attackFrame= new GunAttackAnimation("AnimazioneColtello",4);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Point point) {

        imagePosition=Game.getInstance().getGunModel().update(point);


        //Aggiorno immagine

        if( Game.getInstance().getGunModel().getAttack()) {
            currentImage = attackFrame.update();
        } else
            currentImage=gunAnimation.update(Game.getInstance().getGunModel().angle);

    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
