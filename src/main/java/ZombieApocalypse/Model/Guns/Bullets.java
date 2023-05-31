package ZombieApocalypse.Model.Guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Bullets {
    private final List<Bullet> bullets=new ArrayList();
    private static final Bullets instance=new Bullets();

    public Bullets(){}

    public static Bullets getInstance(){return instance;}

    public void PistolShot(int x, int y, int dimension, double angle){
            this.bullets.add(new BulletPistol(x,y,dimension, angle));

    }
    public void ShotgunShot(int x, int y, int dimension, double angle, BulletShotgun.Type t){
        this.bullets.add(new BulletShotgun(x,y,dimension, angle, t));
    }
    public void GrenadeLaunch(int  x, int  y, int dimension, double angle, int []xP, int [] yP){
        this.bullets.add(new BulletGrenade(x,y,dimension,angle,xP, yP));
    }

    public List<Bullet> getBullets(){return Collections.unmodifiableList(this.bullets);
    }

    public void update(){
        Iterator var1=this.bullets.iterator();
        while(var1.hasNext()){
            Bullet b=(Bullet) var1.next();
            if(!b.update())
                bullets.remove(var1);
        }
    }



}
