package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class BulletGrenade extends Bullet{
    BulletGrenade(int x, int y, int dimension, double angle, Direction d, int tot){
        super(x,y,dimension, true, true);
        velocityX=15;
        velocityY=15;
        isGrenade=true;
        dir=d;
        numFrame=0;
        damage=5;
        totalFrame=tot/15;
    }


private int count=0;
boolean stopAll=false;
    boolean update(){
        if(!stopAll){
        if(Game.getInstance().getBackMenu()){
            stopAll=true;
            menu=true;
            numFrame=25;
            return true;
        }

        if(!ending) {
            count++;
            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && count<totalFrame) {
                if (dir == Direction.DOWN)
                    this.y += this.velocityY;
                if (dir == Direction.LEFT)
                    this.x -= this.velocityX;
                if (dir == Direction.UP)
                    this.y -= this.velocityY;
                if (dir == Direction.RIGHT)
                    this.x += this.velocityX;

                return true;
            } else{
                ending=true;
                numFrame=0;
            }
        }else{
            checkcollision();

            if(numFrame<24){
                numFrame++;
                if(numFrame==10 ) {
                    dimension = dimension + 10;
                    x=x-5;
                    y=y-5;
                }
                if(numFrame==12 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;
                }
                if(numFrame==14 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==16){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==18){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==20){
                    dimension=dimension+10;y=y-5;
                    x=x-5;}
                if(numFrame>22){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                return true;}
            else
                return false;

        }
        return false;
    }else
        return false;}

    private void checkcollision() {
        Point explosion=new Point(x+dimension/2,y+dimension/2);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(numFrame<22 && numFrame>5){
        if(explosion.distance(player)<dimension ){
            Game.getInstance().getPlayerCharacter().hit();
        }
        Enemies.getInstance().checkCollisionHit(x,y,dimension/2, dimension/2, damage);
    }}
}










