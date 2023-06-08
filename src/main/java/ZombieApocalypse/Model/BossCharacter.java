package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;


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
        x=50;
        y=50;
        health=maxHealth;
        hitBox=new Rectangle(x,y,wight,height);
    }


    public void hit() {
        if(countHit==0){

            hit=true;
            health--;
        }
    }

    public void cure(){
        health++;
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


    public void move() {

        if(dir==Settings.movementDirection.RIGHT && Game.getInstance().getWorld().isGround0(getX()+wight+(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()+(10*speed), getY(), centerX, centerY) )
            x += (10*speed);
        else if(dir==Settings.movementDirection.LEFT && Game.getInstance().getWorld().isGround0(getX()-(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()-(10*speed), getY(),centerX,centerY))
            x -= (10*speed);
        else if(dir== Settings.movementDirection.UP && Game.getInstance().getWorld().isGround0(getX(), getY()-(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()-(10*speed),centerX,centerY))
            y -= (10*speed);
        else if(dir==Settings.movementDirection.DOWN && Game.getInstance().getWorld().isGround0(getX(), getY()+height+(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()+(10*speed),centerX,centerY))
            y += (10*speed);
        else
            movement=false;


        hitBox.x=x;
        hitBox.y=y;
    }


}

