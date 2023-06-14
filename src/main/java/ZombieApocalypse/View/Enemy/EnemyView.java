package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;
import ZombieApocalypse.View.CharacterAnimation;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EnemyView  implements  EnemyViewInterface{
    private  final CharacterAnimation runAnimationUp;
    private  final CharacterAnimation runAnimationLeft;
    private  final CharacterAnimation runAnimationDown;
    private  final CharacterAnimation runAnimationRight;
    private  final CharacterAnimation hitUp;
    private  final CharacterAnimation hitDown;
    private  final CharacterAnimation hitLeft;
    private  final CharacterAnimation hitRight;
    Enemies.EnemiesType type;
    private Image currentImage;
    private final ThreadPool emptyImage;
    private final ThreadPool bloodImage;
    Enemy enemyModel;
    public EnemyView(Enemies.EnemiesType value, Enemy enemy){
        Random m=new Random();
        enemyModel=enemy;
        this.type=value;
        if(enemyModel.type== Enemies.EnemiesType.TURRETZOMBIE) {
            runAnimationUp = new CharacterAnimation("Nemici/"+type+"/"+type+"Indietro",4);
            runAnimationDown =new CharacterAnimation("Nemici/"+type+"/"+type+"Avanti",4);
            runAnimationLeft =new CharacterAnimation("Nemici/"+type+"/"+type+"Sinistra",4);
            runAnimationRight =new CharacterAnimation("Nemici/"+type+"/"+type+"Destra",4);
            hitUp=new CharacterAnimation("Nemici/"+type+"/"+type+"DannoAvanti",3);
            hitDown=new CharacterAnimation("Nemici/"+type+"/"+type+"DannoIndietro",3);
            hitLeft=new CharacterAnimation("Nemici/"+type+"/"+type+"DannoSinistra",3);
            hitRight=new CharacterAnimation("Nemici/"+type+"/"+type+"DannoDestra",3);

        }else{
            runAnimationUp = new CharacterAnimation("Nemici/" + type + "/" + type + "Indietro", 3);
            runAnimationDown =new CharacterAnimation("Nemici/" + type + "/" + type + "Avanti", 3);
            runAnimationLeft =new CharacterAnimation("Nemici/" + type + "/" + type + "Sinistra", 3);
            runAnimationRight =new CharacterAnimation("Nemici/" + type + "/" + type + "Destra", 3);
            hitUp =new CharacterAnimation("Nemici/" + type + "/" + type + "DannoAvanti", 3);
            hitDown = new CharacterAnimation("Nemici/" + type + "/" + type + "DannoIndietro", 3);
            hitLeft =new CharacterAnimation("Nemici/" + type + "/" + type + "DannoSinistra", 3);
            hitRight =new CharacterAnimation("Nemici/" + type + "/" + type + "DannoDestra", 3);

        }

        bloodImage= new ThreadPool(ResourcesLoader.getInstance().getImage("/Blood/Sangue"+m.nextInt(0,3)+".png", enemyModel.getWight(), enemyModel.getHeight(), true));
        emptyImage= new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", enemyModel.getWight(), enemyModel.getHeight(), true));


    }







    public void update()  {
        if(enemyModel.dying){
            currentImage=bloodImage.get();
            return;
        }

        if(enemyModel.stopAll ){
            currentImage=emptyImage.get();
            return;
        }
        if(enemyModel.hit){
            if(enemyModel.countHit%2==0){
                if(enemyModel.dir== Settings.movementDirection.UP)
                    currentImage=hitUp.update();
                else if(enemyModel.dir== Settings.movementDirection.DOWN)
                    currentImage=hitDown.update();
                else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                    currentImage=hitRight.update();
                else if(enemyModel.dir== Settings.movementDirection.LEFT)
                    currentImage=hitLeft.update();
                }
        }else{
            if(enemyModel.type== Enemies.EnemiesType.TURRETZOMBIE){
                if(enemyModel.isMoving && (enemyModel.turretCount==0 ||enemyModel.turretCount==5 ||enemyModel.turretCount==10 ||enemyModel.turretCount==15)){
                    if(enemyModel.dir== Settings.movementDirection.UP)
                        currentImage=runAnimationUp.update();
                    else if(enemyModel.dir== Settings.movementDirection.DOWN)
                        currentImage=runAnimationDown.update();
                    else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                        currentImage=runAnimationRight.update();
                    else if(enemyModel.dir== Settings.movementDirection.LEFT)
                        currentImage=runAnimationLeft.update();
                }
                else {
                    if(enemyModel.dir== Settings.movementDirection.UP)
                        currentImage=runAnimationUp.getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.DOWN)
                        currentImage=runAnimationDown.getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                        currentImage=runAnimationRight.getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.LEFT)
                        currentImage=runAnimationLeft.getCurrentImage();

                }

            }else{
        if(enemyModel.isMoving){
            if(enemyModel.dir== Settings.movementDirection.UP)
                currentImage=runAnimationUp.update();
            else if(enemyModel.dir== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.update();
            else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.update();
            else if(enemyModel.dir== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.update();
        }
        else {
            if(enemyModel.dir== Settings.movementDirection.UP)
                currentImage=runAnimationUp.getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.getDefaultImage();

        }}}

    }

    public Image getCurrentImage() {
        return currentImage;
    }


}
