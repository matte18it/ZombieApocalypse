package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class BulletGrenade extends Bullet{
    BulletGrenade(int x, int y, int dimension, double angle, Direction d, int tot){
        super(x,y,dimension,angle, true);
        velocityX=15;
        velocityY=15;
        dir=d;
        numFrame=0;
        totalFrame=tot/15;
    }



boolean stopAll=false;
    boolean update(){
        if(!stopAll){
        if(Game.getInstance().getBackMenu()){
            getView().menu=true;
            stopAll=true;
            numFrame=25;
            return true;
        }
        Point explosion=new Point(x+dimension/2,y+dimension/2);
        Point enemy=new Point(Game.getInstance().getEnemyCharacter().getX()+Game.getInstance().getEnemyCharacter().centerX, Game.getInstance().getEnemyCharacter().getY()+Game.getInstance().getEnemyCharacter().centerY);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(!ending) {
            this.getView().updateView(false, numFrame);

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

                return true;
            } else{
                ending=true;
                numFrame=0;
            }
        }else{
            if (explosion.distance(enemy)<dimension && numFrame<22) {
                Game.getInstance().getEnemyCharacter().hit();
            }
            if(explosion.distance(player)<dimension && numFrame<22){
                Game.getInstance().getPlayerCharacter().hit();
            }
            if(numFrame<24){
                this.getView().updateView(true, numFrame);
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
    } stopAll=false; ;
    return false;}}










