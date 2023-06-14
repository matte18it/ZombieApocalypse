package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletZombieView extends BulletView{
    //Animazione del proiettile sparato dalla zombie
    public BulletZombieView(Bullet b) {
        super(b);
    }
    public void update() {
        if(!bulletModel.ending) {  //proiettile in vita
        if (bulletModel.numFrame<1)
            currentImage = bullet[0].get();
        else if (bulletModel.numFrame == 1)
            currentImage = bullet[1].get();
        else if (bulletModel.numFrame == 2)
            currentImage = bullet[2].get();
        else
            currentImage=bullet[3].get();

    }else{ //animazione della dissolvenza
        if(bulletModel.numFrame==0)
            currentImage=bullet[4].get();
        else if(bulletModel.numFrame==1)
            currentImage=bullet[5].get();
        else if(bulletModel.numFrame==2)
            currentImage=bullet[6].get();
    } }

    }

