package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;
import java.util.Random;

public class BossView implements EnemyViewInterface{

    private CharacterAnimation runAnimationLeft;
    private  CharacterAnimation runAnimationRight;
    private CharacterAnimation hitLeft;
    private CharacterAnimation hitRight;
    private CharacterAnimation idleRight;
    private  CharacterAnimation idleLeft;
    private  CharacterAnimation deathRight;

    private  CharacterAnimation deathLeft;
    private  CharacterAnimation attackLeft1;

    private CharacterAnimation attackRight1;
    private CharacterAnimation attackRight2;

    private CharacterAnimation attackLeft2;






    public final int width = Settings.CELL_SIZEX*3;
    public final int height = Settings.CELL_SIZEY*3;

    private Image currentImage;
    Random m=new Random();
    Enemy enemyModel;
    public BossView(Enemy enemy) {
        enemyModel=enemy;

        String g;
        int i=m.nextInt(0, 10000);
        //if(i==0)
          //  g="Nemici/Boss/BossBlu";
        //else
            g="Nemici/Boss/BossVerde";
            load(g);

        currentImage = idleRight.getDefaultImage();
    }

    public void update() {
    }

    public Image getCurrentImage() {
        return currentImage;
    }
    private void load(String g){
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

    }
}
