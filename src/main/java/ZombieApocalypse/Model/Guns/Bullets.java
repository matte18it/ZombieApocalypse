package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Items.Item;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Bullets {
    private ArrayList<Bullet> bullets=new ArrayList<>();
    private static final Bullets instance=new Bullets();

    public Bullets(){}

    public static Bullets getInstance(){return instance;}



    public synchronized void PistolShot(int x, int y, int dimension, double angle){
            this.bullets.add(new BulletPistol(x,y,dimension, angle));

    }
    public synchronized void ShotgunShot(int x, int y, int dimension, double angle, BulletShotgun.Type t){
        this.bullets.add(new BulletShotgun(x,y,dimension, angle, t));
    }
    public synchronized void GrenadeLaunch(int  x, int  y, int dimension, double angle, Bullet.Direction d, int t){
        this.bullets.add(new BulletGrenade(x,y,dimension,angle,d, t));
    }
    public synchronized void BulletBandit(int  x, int  y, int dimension, double angle, Bullet.Direction dir){
        this.bullets.add(new BulletBandit(x,y,dimension,angle,  dir));
    }
    public synchronized void BulletBoss(int  x, int  y, int dimension, double angle, Bullet.Direction dir){
        this.bullets.add(new BulletBoss(x,y,dimension,angle,  dir));
    }

    public ArrayList<Bullet> getBullets(){return this.bullets;}


    public synchronized void update(){
        bullets.removeIf(str -> !str.update());
    }


    public synchronized void zombieShot(int i, int y,  double angle) {
        this.bullets.add(new BulletZombie(i,y, angle));


    }


    public synchronized void BulletGrenadeBandit(int  x, int  y, int dimension, double angle, Bullet.Direction dir, int t){
        this.bullets.add(new BulletGrenadeBandit(x,y,dimension,angle,  dir, t));
    }

}
