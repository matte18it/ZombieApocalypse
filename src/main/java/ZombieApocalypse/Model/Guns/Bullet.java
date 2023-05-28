package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Gun.BulletView;

import java.awt.*;

public class Bullet {
    private int velocityX;
    private int velocityY;
    enum Direction{UP,DOWN,LEFT,RIGHT};
    private  final BulletView bulletView;
    public enum Weapon{SHOTGUN, PISTOL};

    private Weapon w;
    private int x;
    private int y;
    private double angle;
    final int dimension;
    private Direction dir;
    private Rectangle hitBox;

    Bullet(int x, int y, int dimension, double angle, Weapon weapon){
        this.x=x;
        this.y=y;
        this.dimension=dimension;
        this.angle=angle;
        this.velocityX=10;
        this.velocityY=10;
        this.w=weapon;
        hitBox=new Rectangle(x, y, dimension, dimension);
        bulletView=new BulletView(this.w, this.dimension);
        if(w==Weapon.SHOTGUN)
            dir=Game.getInstance().getShotgunModel().checkDirection(angle);
        if(w==Weapon.PISTOL){
            dir=Game.getInstance().getGunModel().checkDirection(angle);

    }}
    public int getX() {
        return this.x;
    }
    public BulletView getView(){
        return bulletView;
    }

    public int getY() {
        return this.y;
    }

    public int getDimension() {
        return this.dimension;
    }
    int count=0;
    boolean ending =false;

    boolean update(){

        if(!ending) {
            if (hitBox.intersects(Game.getInstance().getEnemyCharacter().hitBox)) {
                Game.getInstance().getEnemyCharacter().hit();
                ending = true;
            }
            if (this.x > 0 && this.x < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY) {
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
            } else
                ending = true;
        }else{
            if(count<3){
                bulletView.isEnding();
                count++;
                return true;}
            else
                return false;

        }
        return false;
    }


    }

