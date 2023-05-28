package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Gun.BulletView;

import java.awt.*;

public class BulletPistol extends Bullet{

    BulletPistol(int x, int y, int dimension, double angle){
        super(x,y,dimension, angle);
            totalFrame=40;
            dir=Game.getInstance().getGunModel().checkDirection(angle);

    }


    boolean update(){

        if(!ending) {
            numFrame++;
            if (hitBox.intersects(Game.getInstance().getEnemyCharacter().hitBox)) {
                Game.getInstance().getEnemyCharacter().hit();
                ending = true;
            }
            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && numFrame<totalFrame) {
                if (dir == Direction.DOWN)
                    this.y += this.velocityY;
                if (dir == Direction.LEFT)
                    this.x -= this.velocityX;
                if (dir == Direction.UP)
                    this.y -= this.velocityY;
                if (dir == Direction.RIGHT)
                    this.x += this.velocityX;
                hitBox.x = x;
                hitBox.y = y;
                return true;
            } else
                ending = true;
        }else{
            if(count<3){
               this.getView().isEnding();
                count++;
                return true;}
            else
                return false;

        }
        return false;
    }


    }

