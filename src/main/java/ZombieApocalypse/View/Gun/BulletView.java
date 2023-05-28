package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;

public class BulletView {

    public Image currentImage;
    public Image [] bullet=new Image[6];
    int count=0;
    boolean ending=false;

    public BulletView(Bullet.Weapon w, int dimension) {
        for(int i=0; i<6; i++)
            bullet[i]= ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola"+i+".png",dimension ,dimension,true);

    }
    public void isEnding(){
        count=0;
        ending=true;
    }

    public void update() {
        if(!ending) {
            if (count<2)
                currentImage = bullet[0];
            else if (count == 2)
                currentImage = bullet[1];
            else
                currentImage = bullet[2];
        }else{
            if(count==0)
                currentImage=bullet[3];
            else if(count==1)
                currentImage=bullet[4];
            else if(count==2)
                currentImage=bullet[5];
        } count++;


    }
    public Image getCurrentImage() {
        return currentImage;
    }
}
