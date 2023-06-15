package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import java.awt.*;


public abstract class GunModel {
    int damage;
    int width;
    int height;
    Rectangle hitBox;
    int centerX;
    int centerY;
    boolean attack=false;
     Point imagePosition=new Point(0,0);
    GunModel(int width, int height, int damage){
        this.width=width;
        this.height=height;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        this.damage=damage;
        centerX=width/2;
        centerY=height/2;
    }
    public boolean getAttack() {
        return attack;
    }
    public abstract void attack();
    public void stopAttack() {
        attack=false;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public  void  update(Point point){}
    public  void  update(){}
    public   boolean isUp(){
        return Game.getInstance().getPlayer().getDir() == Settings.movementDirection.UP || Game.getInstance().getPlayer().getDir() == Settings.movementDirection.DOWN;};
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

    public Point getImagePosition() {
        return imagePosition;
    }
}
