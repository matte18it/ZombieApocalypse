package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;

import java.awt.*;

public class PistolView {
    //gestione della Pistol
    private final GunAnimation gunAnimation=new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL);
    public Image currentImage;
    public PistolView() {
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Point point) { //update secondo la posizione del mouse
        Game.getInstance().getPistol().update(point);
            currentImage=gunAnimation.update(Game.getInstance().getPistol().getAngle());
    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
