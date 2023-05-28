package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public abstract class GunModel {
    public int damage;
    int width;
    int height;
    //Raggio del cerchio, più è piccolo più è grande il cerchio
    int radius;
    int centerX;
    int centerY;
    int xPosy;
    int yPosy;
    boolean attack=false;
    public double angle=0;
    public Point imagePosition;
    public Rectangle hitBox;

    public boolean getAttack() {
        return attack;
    }



    public abstract void attack();
    public void stopAttack() {
        attack=false;
    }
    public double getAngle(){
        return angle;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public  void  update(Point point){};
    public  void  update(){};

    public abstract  boolean isUp();
    public Bullet.Direction checkDirection(double angle){
        if((angle<60 && angle>=0) || (angle>=320)){
            return Bullet.Direction.RIGHT;
        }
        if(angle<140 && angle>=60){
            return Bullet.Direction.UP;

        }
        if(angle<230 && angle>=140){
            return Bullet.Direction.LEFT;

        }

        return Bullet.Direction.DOWN;
    }

    protected void setCenter() {
        centerX=width/2;
        centerY=height/2;
    }
}
