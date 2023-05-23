package ZombieApocalypse.View;

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
    private final GunAnimation attackFrame;
    public Image currentImage;
    public Point imagePosition;

    public GunView() {
        gunAnimation= new GunAnimation("Coltello");
        attackFrame= new GunAnimation("Coltello", true);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Point point) {

        imagePosition=Game.getInstance().getGunModel().update(point);


        //Aggiorno immagine

        if( Game.getInstance().getGunModel().getAttack()) {
            currentImage = attackFrame.updateAttack();
        } else
            currentImage=gunAnimation.update(Game.getInstance().getGunModel().angle);

    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
