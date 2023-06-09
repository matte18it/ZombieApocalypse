package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletBossView extends BulletView{
    private final int rightIndex=0;
    private final int leftIndex=2;

    private final int upIndex=4;

    private final int downIndex=6;

    public BulletBossView(Bullet b) {
        super(b);
    }
    int count=0;


    public void update() {
        if(bulletModel.menu){
            currentImage=emptyImage;
            return;
        }

        if(!bulletModel.ending) {
        if (count==3){
            switch (bulletModel.dir){
                case RIGHT -> currentImage = bullet[rightIndex+1];
                case LEFT ->  currentImage = bullet[leftIndex+1];
                case UP -> currentImage = bullet[upIndex+1];
                case DOWN -> currentImage = bullet[downIndex+1];
            }
            count=0;}
        else{
            switch (bulletModel.dir){
                case RIGHT -> currentImage = bullet[rightIndex];
                case LEFT ->  currentImage = bullet[leftIndex];
                case UP -> currentImage = bullet[upIndex];
                case DOWN -> currentImage = bullet[downIndex];
            }
            count++;}

    }else{

            currentImage=emptyImage;
    } }

    }

