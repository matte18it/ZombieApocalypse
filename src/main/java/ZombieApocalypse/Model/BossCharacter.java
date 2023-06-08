package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;


public class BossCharacter {
    //Gestisce il Boss finale e i suoi movimenti
    int x ;
    int y ;
    final int maxHealth=50;
    public int countHit;

    public void addHit() {
        countHit++;
        if(countHit==30) {
            stopHit();
        }
    }

    public Settings.movementDirection dir= Settings.movementDirection.RIGHT;   //Direzione iniziale
    int health;
    int speed=1;
    boolean movement = false;
    boolean hit=false;
    public int wight;
    public int height;
    public int centerX;
    public int centerY;

    public Rectangle hitBox;

    void startMovementUp() {
        movement = true;
        dir= Settings.movementDirection.UP;
    }
    void startMovementDown() {
        movement = true;
        dir=Settings.movementDirection.DOWN;
    }
    void startMovementRight() {
        movement = true;
        dir=Settings.movementDirection.RIGHT;
    }
    void startMovementLeft() {
        movement = true;
        dir=Settings.movementDirection.LEFT;
    }

    void stopMovement() {
        movement = false;
    }

    public boolean isMoving() {
        return movement;
    }
    public boolean getHit() {
        return hit;
    }
    public void stopHit() {
        hit=false;
        countHit=0;
    }



    int getMaxHealth(){
        return maxHealth;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    BossCharacter(){
        wight=Settings.CELL_SIZEX*3;
        height=Settings.CELL_SIZEY*3;
        centerX=wight/2;
        centerY=height/2;
        x=1000;
        y=50;
        health=maxHealth;
        hitBox=new Rectangle(x,y,wight,height);
    }


    public void hit(int damage) {
            hit=true;
            health=health-damage;

    }


    public void speedUp(){
        if(countSpeed==0){
            speedUp=true;
            speed=4;}
        else
            maxCountSpeed=maxCountSpeed+maxCountSpeed;

    }
    public void stopSpeed(){
        speedUp=false;
        countSpeed=0;
        speed=1;
        maxCountSpeed=300;
    }
    private int maxCountSpeed=300;
    public void addSpeed(){
        countSpeed++;
        if(countSpeed==maxCountSpeed)
            stopSpeed();
    }
    public boolean speedUp=false;
    public int countSpeed=0;
    public boolean die=false;


    public void move() {

        if(dir==Settings.movementDirection.RIGHT && Game.getInstance().getWorld().isGround0(getX()+wight+(20*speed), getY())  && Game.getInstance().getWorld().isPlayer(getX()+wight+(20*speed), getY(), centerX, centerY))
            x += (20*speed);
        else if(dir==Settings.movementDirection.LEFT && Game.getInstance().getWorld().isGround0(getX()-(20*speed), getY()) && Game.getInstance().getWorld().isPlayer(getX()-(20*speed), getY(),centerX,centerY))
            x -= (20*speed);
        else if(dir== Settings.movementDirection.UP && Game.getInstance().getWorld().isGround0(getX(), getY()-(20*speed)) && Game.getInstance().getWorld().isPlayer(getX(), getY()-(20*speed),centerX,centerY))
            y -= (20*speed);
        else if(dir==Settings.movementDirection.DOWN && Game.getInstance().getWorld().isGround0(getX(), getY()+height+(20*speed)) && Game.getInstance().getWorld().isPlayer(getX(), getY()+height+(20*speed),centerX,centerY))
            y += (20*speed);
        else
            movement=false;


        hitBox.x=x;
        hitBox.y=y;
    }
    Random m=new Random();


    public void update() {
        if(hit)
            countHit++;
        if(countHit==20)
            stopHit();
        int f=m.nextInt(0,100);
        if(f<20){
            int i=m.nextInt(0,4);
            switch (i){
                case 0 ->{startMovementUp();}
                case 1 ->{startMovementDown();}
                case 2 ->{startMovementLeft();}
                case 3 ->{startMovementRight();}
            } move();
            //Aggiornamento ad ogni clock delle hitbox
            hitBox.x=x;
            hitBox.y=y;
        } else
            movement=false;  //Fine delle animazioni di movimento

    }


    public void checkHitBox(Rectangle hitBox, int damage) {
        if(this.hitBox.intersects(hitBox))
            hit(damage);
    }


    public void checkDistanceHit(Point d, int damage) {
        Point boss=new Point(x+centerX, y+centerY);

        if(boss.distance(d)<60)
            hit(damage);
    }

    public boolean checkBulletHitBox(Rectangle hitBox, int damage) {
        if(this.hitBox.intersects(hitBox)){
            hit(damage);
        return true;}
        return false;
    }
}

