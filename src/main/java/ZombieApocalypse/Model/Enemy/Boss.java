package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Utility.CountPoint;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;

public class Boss extends Enemy{

    public Boss(int x, int y) {
        super(x, y);

        type= Enemies.EnemiesType.BOSS;
        healt=150;

        super.setSize();
    }
    boolean run=false;
int countRun =0;
    //Per prova
    Random m=new Random();
    public boolean update() {
        //Gestione della Pausa del gioco
        if(Game.getInstance().getBackMenu()){
            stopAll=true;
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
            die=true;
            return true;
            }


        //Gestione delle Hit
        if(hit){
            if(countHit<10){
                countHit++;}
            else
                stopHit();
        }




        Point p = Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);
        int h;
        h=m.nextInt(0,150);
        if(h==0 && countRun==0 && p.distance(turret)>=200) {
            run = true;
            if(attack1)
                stopAttack1();
            if(attack2)
                stopAttack2();
            isMoving=true;
                countRun++;
                if(p.y>=y && p.y<=y+height && p.x<turret.x)
                    moveLeft();
                if(p.y>=y && p.y<=y+height && p.x>=turret.x)
                    moveRight();
                if(p.x>=x && p.x<=x+height && p.y>=turret.y)
                    moveDown();
                if(p.x>=x && p.x<=x+wight && p.y<turret.y)
                    moveUp();
        }
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





        if(p.distance(turret)>=300 && !run ){

            if(turret.y>p.y+50) {
                moveUp();
                stopAttack1();
            }else if(turret.y<p.y-50) {
                moveDown();
                stopAttack1();
            }else{
                if(turret.x>p.x)
                    dir= Settings.movementDirection.LEFT;
                else
                    dir= Settings.movementDirection.RIGHT;
                isMoving=false;

                attack1();}







        }else if((p.distance(turret)>=80 || (turret.y>=p.y+-50 && turret.y<=p.y+50))&& !run){
            if(attack1)
                stopAttack1();
            if(turret.y>p.y )
                moveUp();
            else
                moveDown();
            if(turret.x>p.x)
                moveLeft();
            else
                moveRight();
            if(turret.y>=p.y+-50 && turret.y<=p.y+50)
                dir= Settings.movementDirection.UP;


        }if(p.distance(turret)<80){
            if(run){
                run=false;
                countRun=0;
            }
            if(isMoving)
                isMoving=false;
            attack2();}

        if(attack1 ){
    if(countAttack<23 && countAttack>0)
        countAttack++;
    if(countAttack==23)
        stopAttack1();
    if(countAttack==20)
        shoot();}


        if(attack2){
    if(countAttack<9 && countAttack>0){
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
          Game.getInstance().getPlayerCharacter().hit();
        countAttack++;}
    if(countAttack==9)
        stopAttack2();}






        return true;
    }

    private void shoot() {
        switch (dir){
            case LEFT -> Bullets.getInstance().BulletBoss(x+30,y+35, 25, 0, Bullet.Direction.LEFT);
            case RIGHT -> Bullets.getInstance().BulletBoss(x+wight-50,y+35, 25, 0, Bullet.Direction.RIGHT);
            case UP -> Bullets.getInstance().BulletBoss(x+50,y,25, 0, Bullet.Direction.UP);
            case DOWN -> Bullets.getInstance().BulletBoss(x+50,y+50,25, 0, Bullet.Direction.DOWN);

        }
    }


    private void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+15, y) && Enemies.getInstance().isPlayer(x, y, type)){
            if(!run)
                x=x+4;
            else
                x=x+15;
            hitBox.x=x;
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;} else  isMoving=false;
    }
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


    private void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-15, y) && Enemies.getInstance().isPlayer(x , y,  type)){
            if(!run)
                x=x-4;
            else
                x=x-15;
            hitBox.x=x;
            isMoving=true;
            dir=Settings.movementDirection.LEFT;} else  isMoving=false;
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x,y+height+15) && Enemies.getInstance().isPlayer(x, y ,  type)){
            if(!run)
                y=y+4;
            else
                y=y+15;
            hitBox.y=y;
            isMoving=true;
            dir=Settings.movementDirection.DOWN;} else  isMoving=false;
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-15) && Enemies.getInstance().isPlayer(x, y ,  type)){
            if(!run)
                y=y-4;
            else
                y=y-15;
            hitBox.y=y;
            isMoving=true;
        dir=Settings.movementDirection.UP;} else  isMoving=false;

    }
}
