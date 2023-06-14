package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Guns.Bullet;

public class BulletBossView extends BulletView{
    //Animazione del proiettile sparato dal boss
    //proiettile in ogni posizione ha 2 immagini, gestiti tramite index e index+1
    private final int rightIndex=0;
    private final int leftIndex=2;
    private final int upIndex=4;
    private final int downIndex=6;
    public BulletBossView(Bullet b) {
        super(b);
    }
    private int count=0;
    public void update() {
            if (count==3){
            switch (bulletModel.bulletDir){
                case RIGHT -> currentImage = bullet[rightIndex +1].get();
                case LEFT ->  currentImage = bullet[leftIndex+1].get();
                case UP -> currentImage = bullet[upIndex+1].get();
                case DOWN -> currentImage = bullet[downIndex+1].get();
            }
            count=0;}
        else{
            switch (bulletModel.bulletDir){
                case RIGHT -> currentImage = bullet[rightIndex].get();
                case LEFT ->  currentImage = bullet[leftIndex].get();
                case UP -> currentImage = bullet[upIndex].get();
                case DOWN -> currentImage = bullet[downIndex].get();
            }
            count++;}

    }
    }


