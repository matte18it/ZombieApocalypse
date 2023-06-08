package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletPistolView extends BulletView{

    public BulletPistolView(Bullet b) {
        super(b);
    }

    public void update() {
        if(bulletModel.menu){
            currentImage=emptyImage;
            return;
        }
        if(!bulletModel.ending) {
            if (bulletModel.numFrame<1)
                currentImage = bullet[0];
            else if (bulletModel.numFrame == 1)
                currentImage = bullet[1];
            else
                currentImage = bullet[2];
        }else{
            if(bulletModel.numFrame==0)
                currentImage=bullet[3];
            else if(bulletModel.numFrame==1)
                currentImage=bullet[4];
            else if(bulletModel.numFrame>=2)
                currentImage=emptyImage;
        } }


}

