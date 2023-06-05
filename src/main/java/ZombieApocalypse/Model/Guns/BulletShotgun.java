package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletShotgun extends Bullet{
    public enum Type{CENTER, LEFT, RIGHT};
    Type bulletType;

    BulletShotgun(int x, int y, int dimension, double angle, Type t){
        super(x,y,dimension, false, false);
            totalFrame=8;
            bulletType=t;
            isGrenade=false;
            dir=Game.getInstance().getShotgunModel().checkDirection(angle);

    }


    boolean update(){
        int lateral=3;
        if(Game.getInstance().getBackMenu()){
            }

        if(!ending) {

            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && numFrame<totalFrame) {
                if (dir == Direction.DOWN){
                    if(bulletType==Type.LEFT){
                        this.x-=lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.x+=lateral;
                    }
                        this.y += this.velocityY;}
                if (dir == Direction.LEFT){
                    if(bulletType==Type.LEFT){
                        this.y += lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.y-= lateral;
                }
                    this.x -= this.velocityX;}
                if (dir == Direction.UP){
                    if(bulletType==Type.LEFT){
                        this.x-=lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.x+=lateral;
                }
                        this.y -= this.velocityY;
                }
                if (dir == Direction.RIGHT){
                    if(bulletType==Type.LEFT){
                        this.y -= lateral;
                    }
                    else if(bulletType==Type.RIGHT){
                        this.y+= lateral;
                }
                        this.x += this.velocityX;}
                hitBox.x = x;
                hitBox.y = y;
                numFrame++;
                return true;
            } else{
                ending=true;
                numFrame=0;
            }
        }else{
            if(numFrame<3){
                numFrame++;
                return true;}
            else
                return false;

        }
        return false;
    }


    }

