package ZombieApocalypse.View.Player;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CharacterView {
    //Gestisce la connessione fra Gameloop e Player

    private  final Future<CharacterAnimation> runAnimationUp;
    private final Future<CharacterAnimation> runAnimationLeft;
    private final Future<CharacterAnimation> runAnimationDown;
    private final Future<CharacterAnimation> runAnimationRight;
    private final Future<CharacterAnimation> hitUp;
    private final Future<CharacterAnimation> hitDown;
    private final Future<CharacterAnimation> hitLeft;
    private final Future<CharacterAnimation> hitRight;

    public final int width = Settings.CELL_SIZEX;
    public final int height = Settings.CELL_SIZEY;

    private Image currentImage;

    public CharacterView()  {
        runAnimationUp=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerIndietro", 3));
        runAnimationDown =ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerAvanti", 3));
        runAnimationLeft=ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerSinistra", 3));
        runAnimationRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerDestra", 3));
        hitUp = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/PlayerDannoAvanti", 3));
        hitDown = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/PlayerDannoIndietro", 3));
        hitLeft = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/PlayerDannoSinistra", 3));
        hitRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation("Player/PlayerDannoDestra", 3));

        try {
            currentImage= runAnimationUp.get().getCurrentImage();
        }catch (ExecutionException| InterruptedException e){
            e.printStackTrace();
            System.exit(205);
        }

    }

    public void update() throws ExecutionException, InterruptedException {
        //E' stato colpito? cambia immagine
        if(Game.getInstance().getPlayerCharacter().getHit()) {

            if (Game.getInstance().getPlayerCharacter().countHit % 2 == 0) {
                if(Game.getInstance().getPlayerCharacter().isMoving()){
                if (Game.getInstance().getPlayerDirection() == Settings.movementDirection.UP)
                    currentImage = hitUp.get().update();
                else if (Game.getInstance().getPlayerDirection() == Settings.movementDirection.DOWN)
                    currentImage = hitDown.get().update();
                else if (Game.getInstance().getPlayerDirection() == Settings.movementDirection.LEFT)
                    currentImage = hitLeft.get().update();
                else if (Game.getInstance().getPlayerDirection() == Settings.movementDirection.RIGHT)
                    currentImage = hitRight.get().update();

            } else
                currentImage=hitUp.get().update();}

        }else{
            if(Game.getInstance().getPlayerCharacter().isMoving()){
            if( Game.getInstance().getPlayerDirection()== Settings.movementDirection.UP)
                currentImage = runAnimationUp.get().update();
            else if(  Game.getInstance().getPlayerDirection()== Settings.movementDirection.DOWN)
                currentImage = runAnimationDown.get().update();
            else if(  Game.getInstance().getPlayerDirection()== Settings.movementDirection.LEFT)
                currentImage = runAnimationLeft.get().update();
            else if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.RIGHT)
                currentImage = runAnimationRight.get().update();
           } else{
                if( Game.getInstance().getPlayerDirection()== Settings.movementDirection.UP)
                    currentImage = runAnimationUp.get().getDefaultImage();
                else if(  Game.getInstance().getPlayerDirection()== Settings.movementDirection.DOWN)
                    currentImage = runAnimationDown.get().getDefaultImage();
                else if(  Game.getInstance().getPlayerDirection()== Settings.movementDirection.LEFT)
                    currentImage = runAnimationLeft.get().getDefaultImage();
                else if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.RIGHT)
                    currentImage = runAnimationRight.get().getDefaultImage();

            }
        }

    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public void getUpdate() {
        try{
        update();
    } catch (InterruptedException |ExecutionException e) {
            System.out.println("Errore nel caricamento delle Immagini del player");
            System.exit(208);
        }

        }

    }
