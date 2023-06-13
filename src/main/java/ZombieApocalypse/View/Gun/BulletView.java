package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;

public abstract class BulletView {
    public Image currentImage;
    public ThreadPool[] bullet;
    public ThreadPool emptyImage;
    public ThreadPool grenade;

    Bullet bulletModel;
    Bullet.BulletType bulletType;
    public BulletView(Bullet b){
        bulletModel=b;
        bulletType=bulletModel.type;
        switch (bulletType){
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
        emptyImage=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));

    }
    private void loadBulletBoss() {
        bullet=new ThreadPool[8];
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/Nemici/Boss/BossVerde/Colpo/Colpo" + finalI + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
        emptyImage=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));


    }

    private void loadBulletGrenade() {
        bullet=new ThreadPool[6];
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/Esplosione" + finalI + ".png", bulletModel.getDimension()+(finalI *10), bulletModel.getDimension()+(finalI *10), true));
        }

        grenade = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/Granata0.png", Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), true));
        emptyImage=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));

    }

    private void loadBulletPistol() {
        bullet=new ThreadPool[5];
        for (int i = 0; i < 5; i++) {
            bullet[i] = new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
        emptyImage=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension(), bulletModel.getDimension(), true));


    }

    public Image getCurrentImage() {
        return currentImage;
    }
    public abstract void update();

}
