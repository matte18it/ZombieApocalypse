package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletBoss extends Bullet{

    BulletBoss(int x, int y, int dimension, double angle, Direction direction){
        super(x,y,dimension, BulletType.BOSS);
            totalFrame=80;
            damage=2;
            dir=direction;

    }


    boolean update(){
        if(Game.getInstance().getBackMenu()){
            menu=true;
            }

        if(!ending) {
            if(Enemies.getInstance().checkBulletHitBoxPlayer(hitBox, damage)){
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

                return false;

        }
    }


    }

