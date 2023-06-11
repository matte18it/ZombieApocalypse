package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletGrenadeView extends BulletView{
    public BulletGrenadeView(Bullet b) {
        super(b);
    }

    public void update() {
        if(bulletModel.menu){
            currentImage=emptyImage;
                return;}
            if(!bulletModel.ending){
                currentImage=grenade;

            }else{
                if(bulletModel.numFrame<10 )
                    currentImage=grenade;
                if(bulletModel.numFrame>=10 &&bulletModel.numFrame<12)
                    currentImage=bullet[0];
                if(bulletModel.numFrame>=12 && bulletModel.numFrame<14)
                    currentImage=bullet[1];
                if(bulletModel.numFrame>=14 && bulletModel.numFrame<16){
                    currentImage=bullet[2];}
                if(bulletModel.numFrame == 17)
                    currentImage=bullet[3];
                if(bulletModel.numFrame == 19)
                    currentImage=bullet[4];
                if(bulletModel.numFrame == 21)
                    currentImage=bullet[5];
                if(bulletModel.numFrame>22)
                    currentImage=emptyImage;

            }

    }
}
