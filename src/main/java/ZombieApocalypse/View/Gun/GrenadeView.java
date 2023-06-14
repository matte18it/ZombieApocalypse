package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GrenadeView {
    private final ItemAnimation gunAnimation=new ItemAnimation("Granata",4);;
    public Image currentImage;


    public GrenadeView() {
            currentImage=gunAnimation.getDefaultImage();


    }

    public void update() {
        Game.getInstance().getGrenadeModel().update();
            currentImage=gunAnimation.update();

    }
public void update(Point e){
        Game.getInstance().getGrenadeModel().update(e);
}
    public Image getCurrentImage() {
        return currentImage;
    }

}
