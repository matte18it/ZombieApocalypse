package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;

public class BulletGrenade extends Bullet{
    int [] xP;
    int [] yP;
    int totalEndingFrame;
    BulletGrenade(int x, int y, int dimension, double angle, int [] xPos, int [] yPos){
        super(x,y,dimension,angle, true);
        velocityX=4; //da fare
        velocityY=4;

        xP=xPos;
        yP=yPos;

        totalEndingFrame=30;
    }

    boolean update() {
        if(!ending){
            for(int i=0; i<5; i++){
                if(x<xP[2])
                    x=x+2;
                if(x>xP[2])
                    x=x-2;
                if(y<yP[2])
                    y=y+2;
                if(y>yP[2])
                    y=y-2;
                if(x==xP[2] && y==yP[2])
                    ending=true;
            }








            }



    return true;}
}
