package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Gun.BulletView;

import java.awt.*;

public class Bullet {
     int velocityX;
     int velocityY;
    enum Direction{UP,DOWN,LEFT,RIGHT};
    private  final BulletView bulletView;
    Rectangle hitBox;
     int x;
     int y;
    final int dimension;
     Bullet.Direction dir;
    int count=0;
    int numFrame=0;
    int totalFrame;
    boolean ending=false;
    double angle;

    Bullet(int x, int y, int dimension, double angle){
        this.x=x;
        this.y=y;
        this.dimension=dimension;
        this.velocityX=10;
        this.velocityY=10;
        hitBox=new Rectangle(x, y, dimension, dimension);
        bulletView=new BulletView(this.dimension);
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

    boolean update() {
        return false;

    };


}

