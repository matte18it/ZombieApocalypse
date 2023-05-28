package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;

import java.awt.*;

public class PistolView {
    private final GunAnimation gunAnimation;
    private final GunAttackAnimation attackFrame;
    public Image currentImage;


    public PistolView() {
        gunAnimation= new GunAnimation("Pistola",4);
        attackFrame= new GunAttackAnimation("AnimazioneColtello",4);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Point point) {

        Game.getInstance().getGunModel().update(point);


        //Aggiorno immagine

        //if( Game.getInstance().getGunModel().getAttack()) {
         //   currentImage = attackFrame.update();

            currentImage=gunAnimation.update(Game.getInstance().getGunModel().angle);

    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
