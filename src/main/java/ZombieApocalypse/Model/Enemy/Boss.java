package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Utility.CountPoint;
import ZombieApocalypse.Utility.Settings;
import java.awt.*;
public class Boss extends Enemy{
    Boss(int x, int y) {super(x, y, Enemies.EnemiesType.BOSS);}
    //Gestione della corsa veloce
    private int countRun =0;
    public boolean update() {
        //Gestione della Pausa del gioco
        if(Game.getInstance().getBackMenu()){
            return false;
        }
        //Gestione della Morte
        if(healt<=0 && countDeath<=8){
            countDeath++;
            dying=true;
            if(countDeath==1)
                CountPoint.getInstance().setPointBoss();
            return true;
            }
        if(countDeath>8){
            return false;
            }
        //Gestione delle Hit
        if(hit){
            if(countHit<10){
                countHit++;}
            else
                stopHit();
        }
        Point player=getPlayerPosition();
        Point enemy = getEnemyPosition();
        int h;
        h=random.nextInt(0,150);
        //Gestione dei Pattern
        //3 pattern e 1 random (corsa verso la posizione del player) che cambiano in base alla distanza dal player
        //Pattern della corsa
        if(h==0 && countRun==0 && player.distance(enemy)>=200) {
            run = true;
            if(attack1)
                stopAttack1();
            if(attack2)
                stopAttack2();
            isMoving=true;
                countRun++;
                if(player.y>=y && player.y<=y+height && player.x<enemy.x)
                    moveLeft();
                if(player.y>=y && player.y<=y+height && player.x>=enemy.x)
                    moveRight();
                if(player.x>=x && player.x<=x+height && player.y>=enemy.y)
                    moveDown();
                if(player.x>=x && player.x<=x+wight && player.y<enemy.y)
                    moveUp();
        }
        //Gestione della corsa
        if(countRun>0 && countRun<100){
            countRun++;
            switch (dir){
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case DOWN -> moveDown();
                case UP -> moveUp();
            }
        }
        if(countRun==100){
            countRun=0;
            isMoving=false;
            run=false;
        }
        //1 pattern
        if(player.distance(enemy)>=300 && !run ){
            if(enemy.y>player.y+50) {
                moveUp();
                stopAttack1();
            }else if(enemy.y<player.y-50) {
                moveDown();
                stopAttack1();
            }else{
                if(enemy.x>player.x)
                    dir= Settings.movementDirection.LEFT;
                else
                    dir= Settings.movementDirection.RIGHT;
                isMoving=false;
                attack1();}
            //2 pattern
        }else if((player.distance(enemy)>=80 || (enemy.y>=player.y-50 && enemy.y<=player.y+50))&& !run){
            if(attack1)
                stopAttack1();
            if(enemy.y>player.y )
                moveUp();
            else
                moveDown();
            if(enemy.x>player.x)
                moveLeft();
            else
                moveRight();
            if(enemy.y>=player.y-50 && enemy.y<=player.y+50)
                dir= Settings.movementDirection.UP;
            //3 pattern
        }if(player.distance(enemy)<80){
            if(run){
                run=false;
                countRun=0;
            }
            if(isMoving)
                isMoving=false;
            attack2();}
        //Gestione del attack1 (dalla distanza)
        if(attack1 ){
    if(countAttack<23 && countAttack>0)
        countAttack++;
    if(countAttack==23)
        stopAttack1();
    if(countAttack==20)
        shoot();}
        //Gestione di attack2 (ravvicinato)
        if(attack2){
    if(countAttack<9 && countAttack>0){
        checkHitBox();
        countAttack++;}
    if(countAttack==9)
        stopAttack2();}
        return true;
    }
    private void shoot() {
        //Gestione dello sparo
        switch (dir){
            case LEFT -> Bullets.getInstance().BulletBoss(x+30,y+35,   Bullet.Direction.LEFT);
            case RIGHT -> Bullets.getInstance().BulletBoss(x+wight-50,y+35,   Bullet.Direction.RIGHT);
            case UP -> Bullets.getInstance().BulletBoss(x+50,y,  Bullet.Direction.UP);
            case DOWN -> Bullets.getInstance().BulletBoss(x+50,y+50,  Bullet.Direction.DOWN);
        }
    }
    //Gestione dei 2 attacchi
    private void attack1() {
        if(countAttack==0){
            attack1=true;
        countAttack++;}}
    private void stopAttack1() {
        attack1=false;
        countAttack=0;}
    private void attack2() {
        if(countAttack==0){
        attack2=true;
        countAttack++;}}
    private void stopAttack2() {
        attack2=false;
        countAttack=0;}
}
