package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletPistol extends Bullet{

    BulletPistol(int x, int y, int dimension, double angle){
        super(x,y,dimension, BulletType.PISTOL);

        totalFrame=30;
        damage=5;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        dir=Game.getInstance().getPistolModel().checkDirection(angle);

    }


    boolean update(){
        if(Game.getInstance().getBackMenu()){
            menu=true;
            }

        if(!ending) {
            if(( Enemies.getInstance().checkBulletHitBox(hitBox, damage))){
                ending=true;
                numFrame=0;
            }

            numFrame++;

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
            } else{
                ending = true;
                numFrame=0;
                return true;

            }

        }else{
            if(numFrame<2){
                numFrame++;
                return true;}
            else
                return false;

        }
    }


    }

