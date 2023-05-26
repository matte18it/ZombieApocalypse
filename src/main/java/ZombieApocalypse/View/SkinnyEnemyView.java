package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.EnemyCharacter;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Settings;

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

    final int width = Settings.CELL_SIZEX;
    final int height = Settings.CELL_SIZEY;

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
        // Prendiamo coordinate del giocatore
        int moveToX = PlayerCharacter.getX();
        int moveToY = PlayerCharacter.getY();
        // Settiamo distanza tra giocatore ed enemy
        int diffX = moveToX - EnemyCharacter.getX();
        int diffY = moveToY - EnemyCharacter.getY();
        // diffX e diffY sono componenti di un vettore di cui possiamo calcolare l'angolo prendendo l'arctang
        float angle = (float)Math.atan2(diffY, diffX);



        if(Game.getInstance().getEnemyCharacter().isHitted()){
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
