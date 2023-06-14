package ZombieApocalypse.View.Gun;
import ZombieApocalypse.Model.Enemy.Enemy;
import java.awt.*;

public class BanditPistolView {
    //Animazione dell'arma (pistola) del Bandito
    private final GunAnimation gunAnimation=new GunAnimation("Pistola",4, GunAnimation.GunType.PISTOL);
    public Image currentImage;
    public BanditPistolView() {
            currentImage=gunAnimation.getDefaultImage();
    }
    public void update(Enemy e)  {
            switch (e.dir){
                case LEFT ->  currentImage=gunAnimation.update(0);
                case UP -> currentImage=gunAnimation.update(270);
                case RIGHT -> currentImage=gunAnimation.update(180);
                case DOWN -> currentImage=gunAnimation.update(90);
            }
    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
