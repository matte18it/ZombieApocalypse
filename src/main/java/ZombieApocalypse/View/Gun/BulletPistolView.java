package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletPistolView extends BulletView{

    public BulletPistolView(Bullet b) {
        super(b);
    }

    public void update() {

        if(!bulletModel.ending) {
            if (bulletModel.numFrame<1)
                currentImage = bullet[0].get();
            else if (bulletModel.numFrame == 1)
                currentImage = bullet[1].get();
            else
                currentImage = bullet[2].get();
        }else{
            if(bulletModel.numFrame==0)
                currentImage=bullet[3].get();
            else if(bulletModel.numFrame==1)
                currentImage=bullet[4].get();

        } }


}

