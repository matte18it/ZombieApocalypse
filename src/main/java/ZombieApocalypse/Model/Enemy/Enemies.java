package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Enemies {
    public void addSkinnyZombie(int x, int y) {
        this.enemies.add(new SkinnyZombie(x, y));
    }

    public boolean checkCollision(int x, int y, int ceX, int ceY) {
        Point player=new Point(x+ ceX,y+ceY);
        Point enem=new Point();
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            enem.x=b.getX()+b.getCenterX();
            enem.y=b.getY()+b.getCenterY();
            if(player.distance(enem)<30){
                return false;
            }
        }
        return true;
    }

    public void checkHitBox(Rectangle hitBox, int damage) {
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            if(b.hitBox.intersects(hitBox)){
                b.gettingHit(damage);
            }
        }
    }


    public enum EnemiesType{SKINNYZOMBIE, EMPTY};
    private final List<Enemy> enemies=new ArrayList();
    private static final ZombieApocalypse.Model.Enemy.Enemies instance=new ZombieApocalypse.Model.Enemy.Enemies();

    public Enemies(){}

    public static ZombieApocalypse.Model.Enemy.Enemies getInstance(){return instance;}




    public List<Enemy> getEnemies(){return Collections.unmodifiableList(this.enemies);
    }

    public void update(){
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            if(!b.update()){
                enemies.remove(var1);}

        }
    }
}
