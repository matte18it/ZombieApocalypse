package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;

public class SkinnyEnemyView {
    //Gestisce la connessione fra Gameloop e Player

    private final CharacterAnimation runAnimationUp;
    private final CharacterAnimation runAnimationLeft;
    private final CharacterAnimation runAnimationDown;
    private final CharacterAnimation runAnimationRight;
    private final CharacterAnimation hitUp;
    private final CharacterAnimation hitDown;
    private final CharacterAnimation hitLeft;
    private final CharacterAnimation hitRight;

    public final int width = Settings.CELL_SIZEX;
    public  final int height = Settings.CELL_SIZEY;

    private Image currentImage;

    public SkinnyEnemyView() {
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
        if(Game.getInstance().getEnemyCharacter().getHit()){
            Game.getInstance().getEnemyCharacter().countHit++;

            if( Game.getInstance().getEnemyCharacter().countHit%2==0){

                currentImage = hitUp.update();}
        }else
            currentImage = runAnimationDown.getDefaultImage();

        //Da scrivere
    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
