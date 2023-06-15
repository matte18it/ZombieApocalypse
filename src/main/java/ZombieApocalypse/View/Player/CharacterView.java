package ZombieApocalypse.View.Player;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.CharacterAnimation;
import java.awt.*;
public class CharacterView {
    //Gestisce la animazioni del player
    private  final CharacterAnimation runAnimationUp;
    private final CharacterAnimation runAnimationLeft;
    private final CharacterAnimation runAnimationDown;
    private final CharacterAnimation runAnimationRight;
    private final CharacterAnimation hitUp;
    private final CharacterAnimation hitDown;
    private final CharacterAnimation hitLeft;
    private final CharacterAnimation hitRight;

    public final int width = Settings.CELL_SIZEX;
    public final int height = Settings.CELL_SIZEY;

    private Image currentImage;

    public CharacterView()  {
        runAnimationUp=new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerIndietro", 3);
        runAnimationDown =new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerAvanti", 3);
        runAnimationLeft=new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerSinistra", 3);
        runAnimationRight = new CharacterAnimation("Player/Skin" + GameData.skinAttiva + "/PlayerDestra", 3);
        hitUp = new CharacterAnimation("Player/PlayerDannoAvanti", 3);
        hitDown = new CharacterAnimation("Player/PlayerDannoIndietro", 3);
        hitLeft = new CharacterAnimation("Player/PlayerDannoSinistra", 3);
        hitRight =new CharacterAnimation("Player/PlayerDannoDestra", 3);

            currentImage= runAnimationUp.getCurrentImage();

    }

    public void update() {
        if(Game.getInstance().getPlayer().getHit()) {  //hit
                //aggiornamento un frame si e uno no
            if (Game.getInstance().getPlayer().getCountHit() % 2 == 0) {
                if(Game.getInstance().getPlayer().getMovement()){
                if (Game.getInstance().getPlayer().getDir() == Settings.movementDirection.UP)
                    currentImage = hitUp.update();
                else if (Game.getInstance().getPlayer().getDir() == Settings.movementDirection.DOWN)
                    currentImage = hitDown.update();
                else if (Game.getInstance().getPlayer().getDir() == Settings.movementDirection.LEFT)
                    currentImage = hitLeft.update();
                else if (Game.getInstance().getPlayer().getDir() == Settings.movementDirection.RIGHT)
                    currentImage = hitRight.update();

            } else currentImage=hitUp.update();}

        }else{
            if(Game.getInstance().getPlayer().getMovement()){
            if( Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP)
                currentImage = runAnimationUp.update();
            else if(  Game.getInstance().getPlayer().getDir()== Settings.movementDirection.DOWN)
                currentImage = runAnimationDown.update();
            else if(  Game.getInstance().getPlayer().getDir()== Settings.movementDirection.LEFT)
                currentImage = runAnimationLeft.update();
            else if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.RIGHT)
                currentImage = runAnimationRight.update();
           } else{
                if( Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP)
                    currentImage = runAnimationUp.getDefaultImage();
                else if(  Game.getInstance().getPlayer().getDir()== Settings.movementDirection.DOWN)
                    currentImage = runAnimationDown.getDefaultImage();
                else if(  Game.getInstance().getPlayer().getDir()== Settings.movementDirection.LEFT)
                    currentImage = runAnimationLeft.getDefaultImage();
                else if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.RIGHT)
                    currentImage = runAnimationRight.getDefaultImage();

            }
        }

    }

    public Image getCurrentImage() {
        return currentImage;
    }



    }
