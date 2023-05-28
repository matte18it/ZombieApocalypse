package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class Bullet {
    private int velocityX;
    private int velocityY;
    enum Direction{UP,DOWN,LEFT,RIGHT};
    enum Weapon{SHOTGUN, PISTOL};
    private Weapon w;
    private int x;
    private int y;
    private double angle;
    final int dimension;
    private Direction dir;
    public Image [] bull=new Image[5];
    private Image currentImage;
    public Image getDefaultImage(){
        return bull[2];
    }
    Bullet(int x, int y, int dimension, double angle, Weapon weapon){
        this.x=x;
        this.y=y;
        this.dimension=dimension;
        this.angle=angle;
        this.velocityX=10;
        this.velocityY=10;
        this.w=weapon;
        if(w==Weapon.SHOTGUN)
            dir=Game.getInstance().getShotgunModel().checkDirection(angle);
        if(w==Weapon.PISTOL){
            dir=Game.getInstance().getGunModel().checkDirection(angle);
            for(int i=0; i<5; i++)
                bull[i]=ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola"+i+".png", dimension,dimension,true);
    }}
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDimension() {
        return this.dimension;
    }

    boolean update(){
        if(this.x>-1 && this.x< Settings.WINDOW_SIZEX && this.y>-1 && this.y< Settings.WINDOW_SIZEY) {
            if (dir == Direction.DOWN)
                this.y += this.velocityY;
            if (dir == Direction.LEFT)
                this.x -= this.velocityX;
            if (dir == Direction.UP)
                this.y -= this.velocityY;
            if (dir == Direction.RIGHT)
                this.x += this.velocityX;
            return true;
        }else
            return false;

    }
}
