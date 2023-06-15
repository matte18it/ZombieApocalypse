package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Gun.*;

import java.awt.*;
public abstract class Bullet {
    //Gestione della velocità del proiettile
     int velocity;
     //gestione del ritorno al menù
     public boolean ending=false;
     //direzione del proiettile
     public enum Direction {UP,DOWN,LEFT,RIGHT}
    public Direction bulletDir;
    //Tipo di proiettile
    public enum BulletType{PISTOL, SHOTGUN, ZOMBIE, GRENADE, BOSS}
     public BulletType type;
    //view del proiettile
    private BulletView bulletView;
    Rectangle hitBox;
     int x;
     int y;
    int dimension;
    int damage;
    public int numFrame=0;
    //numero di frame in cui il proiettile deve muoversi
    int totalFrame;
    public int getX() {return this.x;}
    public final BulletView getView(){return bulletView;}
    public int getY() {return this.y;}
    public int getDimension() {return this.dimension;}
    abstract boolean update();
    Bullet(int x, int y,  BulletType bulletType, Direction bulletDir){
        this.bulletDir = bulletDir;
        type=bulletType;
        this.x=x;
        this.y=y;
        switch (type){
            case PISTOL -> setPistol();
            case ZOMBIE -> setZombie();
            case GRENADE -> setGrenade();
            case SHOTGUN -> setShotgun();
            case BOSS -> setBoss();
        }
        hitBox=new Rectangle(x, y, dimension, dimension);
    }
    //Inizializazzione dei tipi di proiettili
    private void setBoss() {
        this.dimension=25;
        totalFrame=80;
        velocity=10;
        bulletView=new BulletBossView(this);
    }

    private void setShotgun() {
        this.dimension=18;
        totalFrame=8;
        damage=4;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        velocity=10;
        bulletView=new BulletPistolView(this);
    }

    private void setGrenade() {
        this.dimension=Game.getInstance().getGrenade().getWidth();
        damage=12;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        velocity=15;
        bulletView=new BulletGrenadeView(this);
    }

    private void setPistol(){
        this.dimension=10;
        totalFrame=30;
        damage=4;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        velocity=10;
        bulletView=new BulletPistolView(this);}

    private void setZombie() {
        this.dimension = 10;
        totalFrame = 20;
        velocity = 10;
        bulletView = new BulletZombieView(this);
    }
}

