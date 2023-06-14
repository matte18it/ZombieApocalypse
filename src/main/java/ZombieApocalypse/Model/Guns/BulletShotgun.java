package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletShotgun extends Bullet{
    //Gestione dei tre tipi di proiettile dello shotgun
    //Ognuno si deve muovere in una direzione diversa
    public enum Type{CENTER, LEFT, RIGHT};
    Type bulletType;
    BulletShotgun(int x, int y, double angle, Type t){
        super(x,y, BulletType.SHOTGUN, Game.getInstance().getShotgunModel().checkDirection(angle));
            bulletType=t;
    }


    boolean update(){
        //distanza fra ogni proiettile
        int lateral=3;
        //Gestione del ritorno al menù
        if(Game.getInstance().getBackMenu()){
            return false;}
        if(!ending) {
            //Gestione del hitbox con i nemici
            if(Enemies.getInstance().checkBulletHitBox(hitBox, damage)){
                ending=true;
                numFrame=0;
            }
            //Gestione del movimento
            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && numFrame<totalFrame) {
                if (bulletDir == Direction.DOWN){
                    if(bulletType==Type.LEFT){
                        this.x-=lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.x+=lateral;
                    }
                        this.y += this.velocity;}
                if (bulletDir == Direction.LEFT){
                    if(bulletType==Type.LEFT){
                        this.y += lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.y-= lateral;
                }
                    this.x -= this.velocity;}
                if (bulletDir == Direction.UP){
                    if(bulletType==Type.LEFT){
                        this.x-=lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.x+=lateral;
                }
                        this.y -= this.velocity;
                }
                if (bulletDir == Direction.RIGHT){
                    if(bulletType==Type.LEFT){
                        this.y -= lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.y+= lateral;
                }
                        this.x += this.velocity;}
                hitBox.x = x;
                hitBox.y = y;
                numFrame++;
            } else{
                ending=true;
                numFrame=0;
            }
            return true;
        }else{
            if(numFrame<2){  //Gestione dell'animazione di fine
                numFrame++;
                return true;}
            else
                return false;

        }
    }


    }

