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

    public void shoot(int x, int y, int dimension, double angle, Bullet.Weapon weapon){
        this.bullets.add(new Bullet(x,y,dimension, angle, weapon));
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
