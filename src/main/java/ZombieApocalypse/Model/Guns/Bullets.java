package ZombieApocalypse.Model.Guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Bullets {
    private final List<Bullet> bullets=new ArrayList<>();
    private static final Bullets instance=new Bullets();

    public Bullets(){}

    public static Bullets getInstance(){return instance;}

    public void PistolShot(int x, int y, int dimension, double angle){
            this.bullets.add(new BulletPistol(x,y,dimension, angle));

    }
    public void ShotgunShot(int x, int y, int dimension, double angle, BulletShotgun.Type t){
        this.bullets.add(new BulletShotgun(x,y,dimension, angle, t));
    }
    public void GrenadeLaunch(int  x, int  y, int dimension, double angle, Bullet.Direction d, int t){
        this.bullets.add(new BulletGrenade(x,y,dimension,angle,d, t));
    }
    public void BulletBandit(int  x, int  y, int dimension, double angle, Bullet.Direction dir){
        this.bullets.add(new BulletBandit(x,y,dimension,angle,  dir));
    }
    public void BulletBoss(int  x, int  y, int dimension, double angle, Bullet.Direction dir){
        this.bullets.add(new BulletBoss(x,y,dimension,angle,  dir));
    }

    public final List<Bullet> getBullets(){return this.bullets;}


    public void update(){
        bullets.removeIf(b -> !b.update());
    }


    public void zombieShot(int i, int y,  double angle) {
        this.bullets.add(new BulletZombie(i,y, angle));


    }


    public void BulletGrenadeBandit(int  x, int  y, int dimension, double angle, Bullet.Direction dir, int t){
        this.bullets.add(new BulletGrenadeBandit(x,y,dimension,angle,  dir, t));
    }

}
