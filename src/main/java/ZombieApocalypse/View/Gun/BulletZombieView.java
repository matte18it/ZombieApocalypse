package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

import java.util.concurrent.ExecutionException;

public class BulletZombieView extends BulletView{
    public BulletZombieView(Bullet b) {
        super(b);
    }


    public void update() throws ExecutionException, InterruptedException{
        if(bulletModel.menu){
            currentImage=emptyImage.get();
            return;
        }

        if(!bulletModel.ending) {
        if (bulletModel.numFrame<1)
            currentImage = bullet[0].get();
        else if (bulletModel.numFrame == 1)
            currentImage = bullet[1].get();
        else if (bulletModel.numFrame == 2)
            currentImage = bullet[2].get();
        else
            currentImage=bullet[3].get();

    }else{
        if(bulletModel.numFrame==0)
            currentImage=bullet[4].get();
        else if(bulletModel.numFrame==1)
            currentImage=bullet[5].get();
        else if(bulletModel.numFrame==2)
            currentImage=bullet[6].get();
        else if(bulletModel.numFrame==3)
            currentImage=emptyImage.get();
    } }

    }

