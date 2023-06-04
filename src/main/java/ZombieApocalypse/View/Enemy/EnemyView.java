package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
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

    private void loadSkinnyZombie() {
        runAnimationUp = new CharacterAnimation("SkinnyZombie/SkinnyZombieIndietro",3);
        runAnimationDown = new CharacterAnimation("SkinnyZombie/SkinnyZombieAvanti",3);
        runAnimationLeft = new CharacterAnimation("SkinnyZombie/SkinnyZombieSinistra",3);
        runAnimationRight = new CharacterAnimation("SkinnyZombie/SkinnyZombieDestra",3);
        hitUp=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoAvanti",3);
        hitDown=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoIndietro",3);
        hitLeft=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoSinistra",3);
        hitRight=new CharacterAnimation("SkinnyZombie/SkinnyZombieDannoDestra",3);
        currentImage = runAnimationDown.getDefaultImage();

    }

    public void update() {

    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
