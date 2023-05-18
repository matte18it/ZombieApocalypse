package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Settings;

import java.awt.*;

public class CharacterView {
    //Gestisce la connessione fra Gameloop e Player

    private final CharacterAnimation runAnimationUp;
    private final CharacterAnimation runAnimationLeft;
    private final CharacterAnimation runAnimationDown;
    private final CharacterAnimation runAnimationRight;

    final int width = Settings.CELL_SIZE;
    final int height = Settings.CELL_SIZE;

    private Image currentImage;

    public CharacterView() {
        runAnimationUp = new CharacterAnimation("PlayerIndietro", 3);
        runAnimationDown = new CharacterAnimation("PlayerAvanti", 3);
        runAnimationLeft = new CharacterAnimation("PlayerSinistra", 3);
        runAnimationRight = new CharacterAnimation("PlayerDestra", 3);
        currentImage = runAnimationDown.getDefaultImage();
    }

    public void update() {

        if(Game.getInstance().getPlayerCharacter().isMoving() && Game.getInstance().getPlayerDirection()== PlayerCharacter.movementDirection.UP)
            currentImage = runAnimationUp.update();
        else if(Game.getInstance().getPlayerCharacter().isMoving() && Game.getInstance().getPlayerDirection()== PlayerCharacter.movementDirection.DOWN)
            currentImage = runAnimationDown.update();
        else if(Game.getInstance().getPlayerCharacter().isMoving() && Game.getInstance().getPlayerDirection()== PlayerCharacter.movementDirection.LEFT)
            currentImage = runAnimationLeft.update();
        else if(Game.getInstance().getPlayerCharacter().isMoving() && Game.getInstance().getPlayerDirection()== PlayerCharacter.movementDirection.RIGHT)
            currentImage = runAnimationRight.update();
        else
            currentImage = runAnimationDown.getDefaultImage();
    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
