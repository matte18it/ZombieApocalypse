package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.GrenadeModel;

import java.awt.*;

public class GrenadeView {
    private final KnifeAnimation gunAnimation;
    public Image currentImage;


    public GrenadeView() {
        gunAnimation= new KnifeAnimation("Granata",4);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update() {
        Game.getInstance().getGrenadeModel().update();

            currentImage=gunAnimation.update();

    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
