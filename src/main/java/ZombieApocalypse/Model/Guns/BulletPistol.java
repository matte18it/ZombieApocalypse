package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class BulletPistol extends Bullet{

    BulletPistol(int x, int y, int dimension, double angle){
        super(x,y,dimension, angle, false);
            totalFrame=40;
            dir=Game.getInstance().getPistolModel().checkDirection(angle);

    }


    boolean update(){
        if(Game.getInstance().getBackMenu()){
            getView().menu=true;}

        if(!ending) {
            this.getView().updateView(false, numFrame);
            numFrame++;
            if (hitBox.intersects(Game.getInstance().getEnemyCharacter().hitBox)) {
                Game.getInstance().getEnemyCharacter().hit();
                ending = true;
                numFrame=0;
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
            } else{
                ending = true;
                numFrame=0;

            }

        }else{
            if(numFrame<3){
               this.getView().updateView(true, numFrame);
                numFrame++;
                return true;}
            else
                return false;

        }
        return false;
    }


    }

