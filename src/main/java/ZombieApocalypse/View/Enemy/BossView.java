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

    private  final Future<CharacterAnimation> runAnimationLeft;
    private  final Future<CharacterAnimation> runAnimationRight;
    private  final Future<CharacterAnimation> hitLeft;
    private  final Future<CharacterAnimation> hitRight;
    private  final Future<CharacterAnimation> idleRight;
    private  final Future<CharacterAnimation> idleLeft;
    private  final Future<CharacterAnimation> deathRight;

    private  final Future<CharacterAnimation> deathLeft;
    private  final Future<CharacterAnimation> attackLeft1;

    private  final Future<CharacterAnimation> attackRight1;
    private  final Future<CharacterAnimation> attackRight2;

    private  final Future<CharacterAnimation> attackLeft2;



    private Image currentImage;
    private final Image emptyImage;
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
        runAnimationLeft = ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Walk/WalkLeft", 10));
        runAnimationRight = ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Walk/WalkRight", 10));
        hitLeft=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Hit/HitLeft",3));
        hitRight=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Hit/HitRight",3));
        idleRight=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Idle/IdleRight", 5));
        idleLeft=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Idle/IdleLeft", 5));
        attackRight1=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Attack1/Attack1Right", 12));
        attackLeft1=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Attack1/Attack1Left", 12));
        attackRight2=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Attack2/Attack2Right", 6));
        attackLeft2=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Attack2/Attack2Left", 6));
        deathLeft=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Death/DeathLeft", 5));
        deathRight=ThreadPool.executeCharacterAnimation(new CharacterAnimation(g+"/Death/DeathRight", 5));
        emptyImage= ResourcesLoader.getInstance().getImage("/ArmieOggetti/EMPTY.png", enemyModel.getWight(), enemyModel.getHeight(), true);
        try {
            currentImage= idleRight.get().getDefaultImage();
        }catch (ExecutionException| InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }}

    private int count=0;
    boolean b=true;


    public void update() throws ExecutionException, InterruptedException {
        if (enemyModel.stopAll) {
            currentImage = emptyImage;
            return;
        }
        if (enemyModel.attack1) {
            if (enemyModel.countAttack % 2 != 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = attackLeft1.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = attackRight1.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = attackLeft1.get().update();
                        else currentImage = attackRight1.get().update();
                    }
                }
            }
            return;

        }
        if (enemyModel.attack2) {
            if (enemyModel.countAttack % 2 != 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = attackLeft2.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = attackRight2.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = attackLeft2.get().update();
                        else currentImage = attackRight2.get().update();
                    }
                }
            }
            return;

        }
        if (enemyModel.dying) {
            if (enemyModel.countDeath % 2 == 0) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = deathLeft.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = deathRight.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = deathLeft.get().update();
                        else currentImage = deathRight.get().update();
                    }
                }
            }
            return;
        }
        if (!enemyModel.hit) {
            if (enemyModel.isMoving) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = runAnimationLeft.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = runAnimationRight.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = runAnimationLeft.get().update();
                        else currentImage = runAnimationRight.get().update();
                    }
                }
            } else if (count == 2) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = idleLeft.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = idleRight.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = idleLeft.get().update();
                        else currentImage = idleRight.get().update();
                    }
                }
                count = 0;
            } else count++;
        } else {
            if (count == 2) {
                switch (enemyModel.dir) {
                    case LEFT -> {
                        currentImage = hitLeft.get().update();
                        b = true;
                    }
                    case RIGHT -> {
                        currentImage = hitRight.get().update();
                        b = false;
                    }
                    case UP, DOWN -> {
                        if (b) currentImage = hitLeft.get().update();
                        else currentImage = hitRight.get().update();
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
