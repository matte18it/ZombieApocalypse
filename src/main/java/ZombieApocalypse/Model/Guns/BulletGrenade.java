package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Vector;

public class BulletGrenade extends Bullet{
    BulletGrenade(int x, int y, int dimension, double angle, Direction d, int tot){
        super(x,y,dimension,angle, true);
        velocityX=15;
        velocityY=15;
        dir=d;
        numFrame=0;
        totalFrame=tot/15;
    }




    boolean update(){
        Point explosion=new Point(x+dimension/2,y+dimension/2);
        Point enemy=new Point(Game.getInstance().getEnemyCharacter().getX()+Game.getInstance().getEnemyCharacter().centerX, Game.getInstance().getEnemyCharacter().getY()+Game.getInstance().getEnemyCharacter().centerY);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(!ending) {
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
            } else
                ending = true;
        }else{
            if (explosion.distance(enemy)<dimension && count<22) {
                Game.getInstance().getEnemyCharacter().hit();
            }
            if(explosion.distance(player)<dimension && count<22){
                Game.getInstance().getPlayerCharacter().hit();
            }
            if(count<23){
                this.getView().isEnding();
                count++;
                if(count==10 ) {
                    dimension = dimension + 10;
                    x=x-5;
                    y=y-5;
                }
                if(count==12 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;
                }
                if(count==14 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(count==16){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(count==18){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(count==20){
                    dimension=dimension+10;y=y-5;
                    x=x-5;}
                if(count>22){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                return true;}
            else
                return false;

        }
        return false;
    }}










