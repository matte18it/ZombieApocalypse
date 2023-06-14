package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletGrenadeView extends BulletView{
    //Animazione della granata
    public BulletGrenadeView(Bullet b) {
        super(b);
    }
    public void update() {
            if(!bulletModel.ending){  //se true la granata non è stata lanciata (il player la ha in mano)
                currentImage=grenade.get();

            }else{  //Lancio della granata
                if(bulletModel.numFrame<10 )
                    currentImage=grenade.get();
                if(bulletModel.numFrame>=10 &&bulletModel.numFrame<12)
                    currentImage=bullet[0].get();
                if(bulletModel.numFrame>=12 && bulletModel.numFrame<14)
                    currentImage=bullet[1].get();
                if(bulletModel.numFrame>=14 && bulletModel.numFrame<16){
                    currentImage=bullet[2].get();}
                if(bulletModel.numFrame == 17)
                    currentImage=bullet[3].get();
                if(bulletModel.numFrame == 19)
                    currentImage=bullet[4].get();
                if(bulletModel.numFrame == 21)
                    currentImage=bullet[5].get();

            }

    }
}
