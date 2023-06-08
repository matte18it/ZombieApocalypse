package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;
import java.util.Random;

public class BossView {

    private final CharacterAnimation runAnimationLeft;
    private final CharacterAnimation runAnimationRight;
    private final CharacterAnimation hitLeft;
    private final CharacterAnimation hitRight;
    private final CharacterAnimation idleRight;
    private final CharacterAnimation idleLeft;
    private final CharacterAnimation deathRight;

    private final CharacterAnimation deathLeft;
    private final CharacterAnimation attackLeft1;

    private final CharacterAnimation attackRight1;
    private final CharacterAnimation attackRight2;

    private final CharacterAnimation attackLeft2;






    public final int width = Settings.CELL_SIZEX;
    public final int height = Settings.CELL_SIZEY;

    private Image currentImage;
    Random m=new Random();

    public BossView() {
        String g;
        int i=m.nextInt(0, 10000);
        //if(i==0)
          //  g="Nemici/Boss/BossBlu";
        //else
            g="Nemici/Boss/BossVerde";
        runAnimationLeft = new CharacterAnimation(g+"/Walk/WalkLeft", 10);
        runAnimationRight = new CharacterAnimation(g+"/Walk/WalkRight", 10);
        hitLeft=new CharacterAnimation(g+"/Hit/HitLeft",3);
        hitRight=new CharacterAnimation(g+"/Hit/HitRight",3);
        idleRight=new CharacterAnimation(g+"/Idle/IdleRight", 5);
        idleLeft=new CharacterAnimation(g+"/Idle/IdleLeft", 5);
        attackRight1=new CharacterAnimation(g+"/Attack1/Attack1Right", 12);
        attackLeft1=new CharacterAnimation(g+"/Attack1/Attack1Left", 12);
        attackRight2=new CharacterAnimation(g+"/Attack2/Attack2Right", 6);
        attackLeft2=new CharacterAnimation(g+"/Attack2/Attack2Left", 6);
        deathLeft=new CharacterAnimation(g+"/Death/DeathLeft", 5);
        deathRight=new CharacterAnimation(g+"/Death/DeathRight", 5);


        currentImage = idleRight.getDefaultImage();
    }

    public void update() {



    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
