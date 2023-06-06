package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.View.Gun.BulletView;

import java.awt.*;

public abstract class Bullet {
     int velocityX;
     int velocityY;
     public boolean menu=false;
     public boolean isGrenade=false;
    enum Direction{UP,DOWN,LEFT,RIGHT};
    private  final BulletView bulletView;
    Rectangle hitBox;
     int x;
     int y;
    int dimension;
     Bullet.Direction dir;
    public int numFrame=0;
    int totalFrame;
    public boolean ending=false;
    int damage;
    public boolean isZombie=false;


    Bullet(int x, int y, int dimension, boolean b, boolean t){
        isZombie=b;
        isGrenade=t;
        this.x=x;
        this.y=y;
        this.dimension=dimension;
        this.velocityX=10;
        this.velocityY=10;
        hitBox=new Rectangle(x, y, dimension, dimension);
        bulletView=new BulletView(this );
        }
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

    abstract boolean update();


}

