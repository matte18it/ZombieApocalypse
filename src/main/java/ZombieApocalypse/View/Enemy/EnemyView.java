package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;

public class EnemyView {
    private  CharacterAnimation runAnimationUp;
    private  CharacterAnimation runAnimationLeft;
    private  CharacterAnimation runAnimationDown;
    private CharacterAnimation runAnimationRight;
    private  CharacterAnimation hitUp;
    private  CharacterAnimation hitDown;
    private  CharacterAnimation hitLeft;
    private  CharacterAnimation hitRight;
    Enemies.EnemiesType type;
    private Image currentImage;
    Enemy enemyModel;
    public EnemyView(Enemies.EnemiesType value, Enemy enemy){
        enemyModel=enemy;
        this.type=value;
        switch (type){
            case SKINNYZOMBIE -> loadSkinnyZombie();
        }


    }




    private void loadSkinnyZombie() {
        runAnimationUp = new CharacterAnimation("SkinnyZombie/SkinnyZombieIndietro",3);
        runAnimationDown = new CharacterAnimation("SkinnyZombie/SkinnyZombieAvanti",3);
        runAnimationLeft = new CharacterAnimation("SkinnyZombie/SkinnyZombieSinistra",3);
        runAnimationRight = new CharacterAnimation("SkinnyZombie/SkinnyZombieDestra",3);
        hitUp=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoAvanti",3);
        hitDown=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoIndietro",3);
        hitLeft=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoSinistra",3);
        hitRight=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoDestra",3);

    }
    public void update(){
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

        }}

    }

    public Image getCurrentImage() {
        return currentImage;
    }


}
