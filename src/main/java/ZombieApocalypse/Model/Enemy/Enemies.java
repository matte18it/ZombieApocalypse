package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Enemies {
    public void addSkinnyZombie(int x, int y) {
        this.enemies.add(new SkinnyZombie(x, y));
    }


    //Controllo della collisione con il player
    public boolean checkCollision(int x, int y, int ceX, int ceY) {
        Point player=new Point(x+ ceX,y+ceY);
        Point enem=new Point();
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            enem.x=b.getX()+ b.centerX;
            enem.y=b.getY()+b.centerY;
            if((b.type==EnemiesType.SKINNYZOMBIE || b.type==EnemiesType.TURRETZOMBIE || b.type==EnemiesType.BANDIT) && player.distance(enem)<16)
                return false;
            if(b.type==EnemiesType.FATZOMBIE && player.distance(enem)<24)
                return false;
            if(b.type==EnemiesType.KIDZOMBIE && player.distance(enem)<13)
                return false;


        }
        return true;
    }
    //Controllo della collisione con l'esplosione della granata (che è di forma rotonda)
    public void checkCollisionHit(int x, int y, int ceX, int ceY, int damage) {
        Point p=new Point(x+ ceX,y+ceY);
        Point enem=new Point();
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            enem.x=b.getX()+b.getCenterX();
            enem.y=b.getY()+b.getCenterY();
            if(b.type==EnemiesType.SKINNYZOMBIE && p.distance(enem)<20)
                b.gettingHit(damage);
            if(b.type==EnemiesType.FATZOMBIE && p.distance(enem)<30)
                b.gettingHit(damage);
            if(b.type==EnemiesType.KIDZOMBIE && p.distance(enem)<15)
                b.gettingHit(damage);
            if(b.type==EnemiesType.TURRETZOMBIE && p.distance(enem)<20)
                b.gettingHit(damage);

            if(GameData.sound) {
                    PlayWav.getInstance().loadSound("/Audio/ZombieHit.wav");
                    PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
                    PlayWav.getInstance().playSound();
                }

        }
        }

    //Controllo della collisione con l'hitbox del coltello

    public void checkHitBox(Rectangle hitBox, int damage) {
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            if(b.hitBox.intersects(hitBox)){
                if(GameData.sound) {
                    PlayWav.getInstance().loadSound("/Audio/ZombieHit.wav");
                    PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
                    PlayWav.getInstance().playSound();
                }
                b.gettingHit(damage);
            }
        }
    }
    //Controllo della collisione con i proiettili (deve restituire booleana per eliminare proiettili)
    public boolean checkBulletHitBox(Rectangle hitBox, int damage) {
        Iterator var1=this.enemies.iterator();
        while(var1.hasNext()){
            Enemy b=(Enemy) var1.next();
            if(b.hitBox.intersects(hitBox)){
                if(GameData.sound) {
                    PlayWav.getInstance().loadSound("/Audio/ZombieHit.wav");
                    PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
                    PlayWav.getInstance().playSound();
                }
                b.gettingHit(damage);
                return true;
            }
        } return false;
    }

    public void addFatZombie(int x, int y) {
        this.enemies.add(new FatZombie(x, y));
    }
    public void addKidZombie(int x, int y) {
        this.enemies.add(new KidZombie(x, y));
    }
    public void addTurretZombie(int x,int y){this.enemies.add(new TurretZombie(x, y));}
    public void addBandit(int x,int y){this.enemies.add(new Bandit(x, y));}


    public boolean checkBulletHitBoxPlayer(Rectangle hitBox, int damage) {
        Rectangle hit=Game.getInstance().getPlayerCharacter().hitBox;
        if(hitBox.intersects(hit)){
            Game.getInstance().getPlayerCharacter().hit();
            return true;
        } return false;

    }


    public enum EnemiesType{SKINNYZOMBIE, FATZOMBIE, KIDZOMBIE,TURRETZOMBIE,BANDIT, EMPTY};
    private final List<Enemy> enemies=new ArrayList<>();
    private static final ZombieApocalypse.Model.Enemy.Enemies instance=new ZombieApocalypse.Model.Enemy.Enemies();

    public Enemies(){}

    public static ZombieApocalypse.Model.Enemy.Enemies getInstance(){return instance;}




    public List<Enemy> getEnemies(){return Collections.unmodifiableList(this.enemies);
    }

    public void update(){
        Iterator<Enemy> e=enemies.iterator();
        while (e.hasNext()){
            Enemy b=e.next();
            if(!b.update()){
                e.remove();
            }
            if(b.type==EnemiesType.BANDIT)
                b.updateGunPosition();


        }
    }
}
