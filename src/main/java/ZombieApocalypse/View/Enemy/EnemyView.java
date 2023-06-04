package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
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
    public EnemyView(Enemies.EnemiesType value){
        this.type=value;
        switch (type){
            case SKINNYZOMBIE -> loadSkinnyZombie();
        }

    }
    private boolean isMoving=false;
    Settings.movementDirection direction= Settings.movementDirection.DOWN;

    public void isMoving(Settings.movementDirection m) {
        direction=m;
        isMoving=true;
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
        if(isMoving){
            if(direction== Settings.movementDirection.UP)
                currentImage=runAnimationUp.update();
            else if(direction== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.update();
            else if(direction== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.update();
            else if(direction== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.update();
            isMoving=false;
        }
        else {
            if(direction== Settings.movementDirection.UP)
                currentImage=runAnimationUp.getDefaultImage();
            else if(direction== Settings.movementDirection.DOWN)
                currentImage=runAnimationDown.getDefaultImage();
            else if(direction== Settings.movementDirection.RIGHT)
                currentImage=runAnimationRight.getDefaultImage();
            else if(direction== Settings.movementDirection.LEFT)
                currentImage=runAnimationLeft.getDefaultImage();

        }

    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
