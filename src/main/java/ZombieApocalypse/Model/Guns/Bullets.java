package ZombieApocalypse.Model.Guns;
import java.util.*;
public class Bullets {
    //Lista per update dei bullets
    private final List<Bullet> bullets=new Vector<>();
    public List<Bullet> getBullets(){return this.bullets;}
    private static final Bullets instance=new Bullets();
    public Bullets(){}
    public static Bullets getInstance(){return instance;}
    //add proiettile
    public  void PistolShot(int x, int y, double angle){
    this.bullets.add(new BulletPistol(x,y, angle));}
    public  void ShotgunShot(int x, int y, double angle, BulletShotgun.Type t){
        this.bullets.add(new BulletShotgun(x,y, angle, t));}
    public  void GrenadeLaunch(int  x, int  y,  Bullet.Direction d, int t){
        this.bullets.add(new BulletGrenade(x,y,d, t));}
    public  void BulletBandit(int  x, int  y, Bullet.Direction dir){
        this.bullets.add(new BulletBandit(x,y,  dir));
    }
    public  void BulletBoss(int  x, int  y,  Bullet.Direction dir){
        this.bullets.add(new BulletBoss(x,y,  dir));
    }
    public  void zombieShot(int i, int y,  double angle) {
        this.bullets.add(new BulletZombie(i,y, angle));
    }
    public  void BulletGrenadeBandit(int  x, int  y, Bullet.Direction dir, int t){
        this.bullets.add(new BulletGrenadeBandit(x,y,  dir, t));
    }
    //Gestione dell'update
    public  void update(){
        synchronized (bullets){
        bullets.removeIf(str -> !str.update());}
    }




}
