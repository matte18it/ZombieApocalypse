package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

import java.util.concurrent.ExecutionException;

public class BulletBossView extends BulletView{
    private final int rightIndex=0;
    private final int leftIndex=2;

    private final int upIndex=4;

    private final int downIndex=6;

    public BulletBossView(Bullet b) {
        super(b);
    }
    int count=0;


    public void update() throws ExecutionException, InterruptedException {

        if(bulletModel.menu){
            currentImage=emptyImage.get();
            return;
        }

        if(!bulletModel.ending) {
        if (count==3){
            switch (bulletModel.dir){
                case RIGHT -> currentImage = bullet[rightIndex+1].get();
                case LEFT ->  currentImage = bullet[leftIndex+1].get();
                case UP -> currentImage = bullet[upIndex+1].get();
                case DOWN -> currentImage = bullet[downIndex+1].get();
            }
            count=0;}
        else{
            switch (bulletModel.dir){
                case RIGHT -> currentImage = bullet[rightIndex].get();
                case LEFT ->  currentImage = bullet[leftIndex].get();
                case UP -> currentImage = bullet[upIndex].get();
                case DOWN -> currentImage = bullet[downIndex].get();
            }
            count++;}

    }else{

            currentImage=emptyImage.get();
    } }

    }

