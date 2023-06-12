package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GrenadeView {
    private final Future<ItemAnimation> gunAnimation;
    public Image currentImage;


    public GrenadeView() {
        gunAnimation= ThreadPool.executeItemAnimation( new ItemAnimation("Granata",4));
        try {
            currentImage=gunAnimation.get().getDefaultImage();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }

    }

    public void update() {
        Game.getInstance().getGrenadeModel().update();
        try {
            currentImage=gunAnimation.get().update();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }




    }
public void update(Point e){
        Game.getInstance().getGrenadeModel().update(e);
}
    public Image getCurrentImage() {
        return currentImage;
    }

}
