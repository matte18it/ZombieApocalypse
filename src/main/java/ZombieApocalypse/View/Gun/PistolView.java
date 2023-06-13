package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PistolView {
    private final GunAnimation gunAnimation=new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL);
    public Image currentImage;


    public PistolView() {

        currentImage=gunAnimation.getDefaultImage();
    }

    public void update(Point point) {

        Game.getInstance().getPistolModel().update(point);
            currentImage=gunAnimation.update(Game.getInstance().getPistolModel().angle);
    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
