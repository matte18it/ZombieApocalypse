package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;

public abstract class BulletView {
    public Image currentImage;
    public Image [] bullet;
    public Image emptyImage;
    public Image grenade;

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
        bullet=new Image[7];
        for (int i = 0; i < 7; i++)
            bullet[i] = ResourcesLoader.getInstance().getImage("/Nemici/TURRETZOMBIE/Vomito" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true);
    }
    private void loadBulletBoss() {
        bullet=new Image[8];
        for (int i = 0; i < 8; i++)
                bullet[i] = ResourcesLoader.getInstance().getImage("/Nemici/Boss/BossVerde/Colpo/Colpo" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true);

    }

    private void loadBulletGrenade() {
        bullet=new Image[6];
        for (int i = 0; i < 6; i++)
            bullet[i] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/Esplosione" + i + ".png", bulletModel.getDimension()+(i*10), bulletModel.getDimension()+(i*10), true);

        grenade = ResourcesLoader.getInstance().getImage("/ArmieOggetti/Granata0.png", Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), true);
        emptyImage=ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true);

    }

    private void loadBulletPistol() {
        bullet=new Image[5];
        for (int i = 0; i < 5; i++)
            bullet[i] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola" + i + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true);
        emptyImage=ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension(), bulletModel.getDimension(), true);


    }

    public Image getCurrentImage() {
        return currentImage;
    }
    public abstract void update();
}
