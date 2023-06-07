package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.View.Gun.*;

import java.awt.*;

public abstract class Bullet {
     int velocityX;
     int velocityY;
     public boolean menu=false;
    public enum Direction{UP,DOWN,LEFT,RIGHT};
    public enum BulletType{PISTOL, SHOTGUN, ZOMBIE, GRENADE};
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
    public BulletType type;


    Bullet(int x, int y, int dimension, BulletType bulletType){
        type=bulletType;
        this.x=x;
        this.y=y;
        this.dimension=dimension;
        this.velocityX=10;
        this.velocityY=10;
        hitBox=new Rectangle(x, y, dimension, dimension);
        switch (type){
            case ZOMBIE -> bulletView=new BulletZombieView(this);
            case GRENADE -> bulletView=new BulletGrenadeView(this);
            default -> bulletView=new BulletPistolView(this);
        }
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

