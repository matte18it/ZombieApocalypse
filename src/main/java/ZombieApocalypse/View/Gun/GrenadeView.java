package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;

import java.awt.*;

public class GrenadeView {
    private final ItemAnimation gunAnimation;
    public Image currentImage;


    public GrenadeView() {
        gunAnimation= new ItemAnimation("Granata",4);
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
