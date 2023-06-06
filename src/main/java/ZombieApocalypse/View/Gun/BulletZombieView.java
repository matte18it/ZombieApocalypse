package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletZombieView extends BulletView{
    public BulletZombieView(Bullet b) {
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
        else if (bulletModel.numFrame == 2)
            currentImage = bullet[2];
        else
            currentImage=bullet[3];

    }else{
        if(bulletModel.numFrame==0)
            currentImage=bullet[4];
        else if(bulletModel.numFrame==1)
            currentImage=bullet[5];
        else if(bulletModel.numFrame==2)
            currentImage=bullet[6];
        else if(bulletModel.numFrame==3)
            currentImage=emptyImage;
    } }

    }

