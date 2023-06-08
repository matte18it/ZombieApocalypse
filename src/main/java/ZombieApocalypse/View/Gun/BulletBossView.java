package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletBossView extends BulletView{
    public BulletBossView(Bullet b) {
        super(b);
    }
    int count=0;


    public void update() {
        if(bulletModel.menu){
            currentImage=emptyImage;
            return;
        }

        if(!bulletModel.ending) {
        if (count==3){
            currentImage = bullet[1];count=0;}
        else{
            currentImage=bullet[0];
            count++;}

    }else{

            currentImage=emptyImage;
    } }

    }

