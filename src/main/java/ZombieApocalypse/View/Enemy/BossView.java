package ZombieApocalypse.View.Enemy;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;
import ZombieApocalypse.View.CharacterAnimation;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BossView implements EnemyViewInterface{

    private  final CharacterAnimation runAnimationLeft;
    private  final CharacterAnimation runAnimationRight;
    private  final CharacterAnimation hitLeft;
    private  final CharacterAnimation hitRight;
    private  final CharacterAnimation idleRight;
    private  final CharacterAnimation idleLeft;
    private  final CharacterAnimation deathRight;

    private  final CharacterAnimation deathLeft;
    private  final CharacterAnimation attackLeft1;

    private  final CharacterAnimation attackRight1;
    private  final CharacterAnimation attackRight2;

    private  final CharacterAnimation attackLeft2;

    private Image currentImage;
    private final ThreadPool  emptyImage;
    Random m=new Random();
    Enemy enemyModel;
    public BossView(Enemy enemy) {
        enemyModel=enemy;
        String g;
        int i=m.nextInt(0, 500);  //Gestione dello Shiny
        if(i==0)
            g="Nemici/Boss/BossBlu";
        else
            g="Nemici/Boss/BossVerde";
        idleRight=new CharacterAnimation(g+"/Idle/IdleRight", 5);
        runAnimationLeft =new CharacterAnimation(g+"/Walk/WalkLeft", 10);
        runAnimationRight =new CharacterAnimation(g+"/Walk/WalkRight", 10);
        hitLeft=new CharacterAnimation(g+"/Hit/HitLeft",3);
        hitRight=new CharacterAnimation(g+"/Hit/HitRight",3);
        idleLeft=new CharacterAnimation(g+"/Idle/IdleLeft", 5);
        attackRight1=new CharacterAnimation(g+"/Attack1/Attack1Right", 12);
        attackLeft1=new CharacterAnimation(g+"/Attack1/Attack1Left", 12);
        attackRight2=new CharacterAnimation(g+"/Attack2/Attack2Right", 6);
        attackLeft2=new CharacterAnimation(g+"/Attack2/Attack2Left", 6);
        deathLeft=new CharacterAnimation(g+"/Death/DeathLeft", 5);
        deathRight=new CharacterAnimation(g+"/Death/DeathRight", 5);
        emptyImage= new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", enemyModel.getWight(), enemyModel.getHeight(), true));
        currentImage= idleRight.getDefaultImage();
        }

    private int count=0;
    boolean b=true;


    public void update()  {
        if (enemyModel.attack1) {
            if (enemyModel.countAttack % 2 != 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = attackLeft1.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = attackRight1.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = attackLeft1.update();
                        else currentImage = attackRight1.update();
                    }
                }
            }
            return;

        }
        if (enemyModel.attack2) {
            if (enemyModel.countAttack % 2 != 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = attackLeft2.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = attackRight2.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = attackLeft2.update();
                        else currentImage = attackRight2.update();
                    }
                }
            }
            return;

        }
        if (enemyModel.dying) {
            if (enemyModel.countDeath % 2 == 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = deathLeft.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = deathRight.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = deathLeft.update();
                        else currentImage = deathRight.update();
                    }
                }
            }
            return;
        }
        if (!enemyModel.hit) {
            if (enemyModel.isMoving) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = runAnimationLeft.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = runAnimationRight.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = runAnimationLeft.update();
                        else currentImage = runAnimationRight.update();
                    }
                }
            } else if (count == 2) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = idleLeft.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = idleRight.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = idleLeft.update();
                        else currentImage = idleRight.update();
                    }
                }
                count = 0;
            } else count++;
        } else {
            if (count == 2) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = hitLeft.update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = hitRight.update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = hitLeft.update();
                        else currentImage = hitRight.update();
                    }
                }
                count = 0;
            } else count++;
        }
    }

    public Image getCurrentImage() {
        return currentImage;
    }

}
