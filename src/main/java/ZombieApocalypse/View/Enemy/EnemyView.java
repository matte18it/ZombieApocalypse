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
    private  final Future<CharacterAnimation> runAnimationUp;
    private  final Future<CharacterAnimation> runAnimationLeft;
    private  final Future<CharacterAnimation> runAnimationDown;
    private  final Future<CharacterAnimation> runAnimationRight;
    private  final Future<CharacterAnimation> hitUp;
    private  final Future<CharacterAnimation> hitDown;
    private  final Future<CharacterAnimation> hitLeft;
    private  final Future<CharacterAnimation> hitRight;
    Enemies.EnemiesType type;
    private Image currentImage;
    private final Future<Image> emptyImage;
    private final Future<Image> bloodImage;
    Enemy enemyModel;
    public EnemyView(Enemies.EnemiesType value, Enemy enemy){
        Random m=new Random();
        enemyModel=enemy;
        this.type=value;
        if(enemyModel.type== Enemies.EnemiesType.TURRETZOMBIE) {
            runAnimationUp = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"Indietro",4));
            runAnimationDown =ThreadPool.executeCharacterAnimation( new CharacterAnimation("Nemici/"+type+"/"+type+"Avanti",4));
            runAnimationLeft =ThreadPool.executeCharacterAnimation( new CharacterAnimation("Nemici/"+type+"/"+type+"Sinistra",4));
            runAnimationRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"Destra",4));
            hitUp=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"DannoAvanti",3));
            hitDown=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"DannoIndietro",3));
            hitLeft=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"DannoSinistra",3));
            hitRight=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/"+type+"/"+type+"DannoDestra",3));

        }else{
            runAnimationUp = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "Indietro", 3));
            runAnimationDown = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "Avanti", 3));
            runAnimationLeft = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "Sinistra", 3));
            runAnimationRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "Destra", 3));
            hitUp = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "DannoAvanti", 3));
            hitDown = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "DannoIndietro", 3));
            hitLeft = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "DannoSinistra", 3));
            hitRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Nemici/" + type + "/" + type + "DannoDestra", 3));

        }

        bloodImage= ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/Blood/Sangue"+m.nextInt(0,3)+".png", enemyModel.getWight(), enemyModel.getHeight(), true));
        emptyImage= ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", enemyModel.getWight(), enemyModel.getHeight(), true));


    }







    public void update() throws ExecutionException, InterruptedException {
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
                    currentImage=hitUp.get().update();
                else if(enemyModel.dir== Settings.movementDirection.DOWN)
                    currentImage=hitDown.get().update();
                else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                    currentImage=hitRight.get().update();
                else if(enemyModel.dir== Settings.movementDirection.LEFT)
                    currentImage=hitLeft.get().update();
                }
        }else{
            if(enemyModel.type== Enemies.EnemiesType.TURRETZOMBIE){
                if(enemyModel.isMoving && (enemyModel.turretCount==0 ||enemyModel.turretCount==5 ||enemyModel.turretCount==10 ||enemyModel.turretCount==15)){
                    if(enemyModel.dir== Settings.movementDirection.UP)
                        currentImage=runAnimationUp.get().update();
                    else if(enemyModel.dir== Settings.movementDirection.DOWN)
                        currentImage=runAnimationDown.get().update();
                    else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                        currentImage=runAnimationRight.get().update();
                    else if(enemyModel.dir== Settings.movementDirection.LEFT)
                        currentImage=runAnimationLeft.get().update();
                }
                else {
                    if(enemyModel.dir== Settings.movementDirection.UP)
                        currentImage=runAnimationUp.get().getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.DOWN)
                        currentImage=runAnimationDown.get().getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                        currentImage=runAnimationRight.get().getCurrentImage();
                    else if(enemyModel.dir== Settings.movementDirection.LEFT)
                        currentImage=runAnimationLeft.get().getCurrentImage();

                }

            }else{
        if(enemyModel.isMoving){
            if(enemyModel.dir== Settings.movementDirection.UP)
                currentImage=runAnimationUp.get().update();
            else if(enemyModel.dir== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.get().update();
            else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.get().update();
            else if(enemyModel.dir== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.get().update();
        }
        else {
            if(enemyModel.dir== Settings.movementDirection.UP)
                currentImage=runAnimationUp.get().getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.get().getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.get().getDefaultImage();
            else if(enemyModel.dir== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.get().getDefaultImage();

        }}}

    }

    public Image getCurrentImage() {
        return currentImage;
    }


}
