package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletZombie extends Bullet{

    BulletZombie(int x, int y,  double angle){
        super(x,y, BulletType.ZOMBIE, Game.getInstance().getPistol().checkDirection(angle));
    }


    boolean update(){
        //Gestione del ritorno al menù
        if(Game.getInstance().getBackMenu())
            return false;
        if(!ending) {
            if(Enemies.getInstance().checkBulletHitBoxPlayer(hitBox)){
                ending=true;
                numFrame=0;
            }
            //Gestione dei movimenti
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
            } else{
                ending = true;
                numFrame=0;

            }
            return true;

        }else{
            if(numFrame<3){ //Gestione di fine animazione
                numFrame++;
                return true;}
            else
                return false;
        }
    }


}
