package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;

public abstract class BulletView {
    //loading delle animazioni dei proiettili
    public Image currentImage;
    public ThreadPool[] bullet;
    public ThreadPool grenade;
    Bullet bulletModel;
    Bullet.BulletType bulletType;
    public BulletView(Bullet b){
        bulletModel=b;
        bulletType=bulletModel.type;
        switch (bulletType){ //loading personalizzato per ognuno
            case PISTOL, SHOTGUN -> loadBulletPistol();
            case GRENADE -> loadBulletGrenade();
            case ZOMBIE -> loadBulletZombie();
            case BOSS -> loadBulletBoss();
        }
    }
    private void loadBulletZombie() {
        bullet=new ThreadPool[7];
        for (int i = 0; i < 7; i++) {
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/Nemici/TURRETZOMBIE/Vomito" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }

    }
    private void loadBulletBoss() {
        bullet=new ThreadPool[8];
        for (int i = 0; i < 8; i++) {
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/Nemici/Boss/BossVerde/Colpo/Colpo" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
    }
    private void loadBulletGrenade() {
        bullet=new ThreadPool[6];
        for (int i = 0; i < 6; i++) {
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/Esplosione" + i + ".png", bulletModel.getDimension()+(i *10), bulletModel.getDimension()+(i *10), true));
        }
        grenade = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/Granata0.png", Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), true));
    }

    private void loadBulletPistol() {
        bullet=new ThreadPool[5];
        for (int i = 0; i < 5; i++) {
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
    }

    public Image getCurrentImage() {
        return currentImage;
    }
    public abstract void update();

}
