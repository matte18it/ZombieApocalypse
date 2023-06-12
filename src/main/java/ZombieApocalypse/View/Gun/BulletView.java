package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class BulletView {
    public Image currentImage;
    public Future<Image>[] bullet;
    public Future<Image> emptyImage;
    public Future<Image> grenade;

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
        bullet=new Future[7];
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            bullet[i] = ThreadPool.getExecutor().submit(()-> ResourcesLoader.getInstance().getImage("/Nemici/TURRETZOMBIE/Vomito" + finalI + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
        emptyImage=ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));

    }
    private void loadBulletBoss() {
        bullet=new Future[8];
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            bullet[i] = ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/Nemici/Boss/BossVerde/Colpo/Colpo" + finalI + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
        emptyImage=ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));


    }

    private void loadBulletGrenade() {
        bullet=new Future[6];
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            bullet[i] = ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/Esplosione" + finalI + ".png", bulletModel.getDimension()+(finalI *10), bulletModel.getDimension()+(finalI *10), true));
        }

        grenade = ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/Granata0.png", Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), true));
        emptyImage=ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension()+50, bulletModel.getDimension()+50, true));

    }

    private void loadBulletPistol() {
        bullet=new Future[5];
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            bullet[i] = ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/ProiettilePistola" + finalI + ".png", bulletModel.getDimension(), bulletModel.getDimension(), true));
        }
        emptyImage=ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", bulletModel.getDimension(), bulletModel.getDimension(), true));


    }

    public Image getCurrentImage() {
        return currentImage;
    }
    public abstract void update() throws ExecutionException, InterruptedException;
    public void loadUpdate(){
        try {
            update();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            System.exit(207);

        }
    }
}
