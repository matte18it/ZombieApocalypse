package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;

public class BulletView {

    public Image currentImage;
    public Image [] bullet=new Image[6];
    public Image [] grenadeBullet=new Image[7];
    public Image [] grenade=new Image[4];

    Bullet bulletModel;

    public BulletView( Bullet b) {
        bulletModel=b;
        if(bulletModel.isGrenade) {
            for (int i = 0; i < 6; i++)
                grenadeBullet[i] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/Esplosione" + i + ".png", bulletModel.getDimension()+(i*10), bulletModel.getDimension()+(i*10), true);
            for (int i = 0; i < 4; i++)
                grenade[i] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/Granata" + i + ".png", Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), true);
        }if (bulletModel.isZombie){
            for (int i = 0; i < 6; i++)
                bullet[i] = ResourcesLoader.getInstance().getImage("/Nemici/TURRETZOMBIE/Vomito" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true);


        }else {

            for (int i = 0; i < 6; i++)
                bullet[i] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true);

    }}


    public void update() {


        if(bulletModel.isGrenade){
            if(bulletModel.menu){
                currentImage=grenadeBullet[6];

            return;}
            if(!bulletModel.ending){
                currentImage=grenade[0];

            }else{
                if(bulletModel.numFrame<10 )
                    currentImage=grenade[0];
                if(bulletModel.numFrame>=10 &&bulletModel.numFrame<12)
                    currentImage=grenadeBullet[0];
                if(bulletModel.numFrame>=12 && bulletModel.numFrame<14)
                    currentImage=grenadeBullet[1];
                if(bulletModel.numFrame>=14 && bulletModel.numFrame<16){
                    currentImage=grenadeBullet[2];}
                if(bulletModel.numFrame == 17)
                    currentImage=grenadeBullet[3];
                if(bulletModel.numFrame == 19)
                    currentImage=grenadeBullet[4];
                if(bulletModel.numFrame == 21)
                    currentImage=grenadeBullet[5];
                if(bulletModel.numFrame>22)
                    currentImage=grenadeBullet[6];

            }
        }else if(bulletModel.isZombie){
            if(bulletModel.menu){
                currentImage=bullet[5];
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
                else if(bulletModel.numFrame==2)
                    currentImage=bullet[5];
            } }

        else{
            if(bulletModel.menu){
                currentImage=bullet[5];
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
                else if(bulletModel.numFrame==2)
                    currentImage=bullet[5];
                } }


    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
