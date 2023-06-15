package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import java.awt.*;

public class GrenadeView {
    //Animazione della granata
    private final ItemAnimation itemAnimation =new ItemAnimation("Granata",4);;
    public Image currentImage;

    public GrenadeView() {
            currentImage= itemAnimation.getDefaultImage();
    }
//aggiornamento senza mouse: si basa sulla posizione del player
//aggiornamento con mouse: si basa su un movimento del mouse
    public void update() {
        Game.getInstance().getGrenade().update();  //il model aggiorna il punto
            currentImage= itemAnimation.update();
    }
public void update(Point e){
        Game.getInstance().getGrenade().update(e);
}
    public Image getCurrentImage() {
        return currentImage;
    }

}
