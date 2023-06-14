package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletBoss extends Bullet{
    BulletBoss(int x, int y,  Direction direction){super(x,y, BulletType.BOSS, direction);}
    boolean update(){
        //Gestione del ritorno al menù
        if(Game.getInstance().getBackMenu()){
            return false;}
        //Gestione dell hitbox
        if(!ending) {
            if(Enemies.getInstance().checkBulletHitBoxPlayer(hitBox)){
                ending=true;
                numFrame=0;
            }
            //Gestione del movimento
            numFrame++;
            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && numFrame<totalFrame) {
                if (bulletDir == Direction.DOWN)
                    this.y += this.velocity;
                if (bulletDir == Direction.LEFT)
                    this.x -= this.velocity;
                if (bulletDir == Direction.UP)
                    this.y -= this.velocity;
                if (bulletDir == Direction.RIGHT)
                    this.x += this.velocity;
                hitBox.x = x;
                hitBox.y = y;
            } else{ //gestione della fine del proiettile
                ending = true;
                numFrame=0;
            }
            return true;
        }else{

                return false;
        }
    }
    }

