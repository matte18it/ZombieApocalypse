package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;
import ZombieApocalypse.View.MenuBarView;

import java.awt.*;

public class Character {

    int x ;
    int y ;
    final int maxHealth=6;
    public int countHit;
    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;
    int health;
    boolean movement = false;
    boolean hit=false;
    int wight;
    int height;
    int centerX=wight/2;
    int centerY=height/2;

    public Rectangle hitBox;
    void setHitbox(){
        hitBox=new Rectangle(x,y,wight,height);
    }
    void startMovementUp() {
        movement = true;
        dir=movementDirection.UP;
    }
    void startMovementDown() {
        movement = true;
        dir=movementDirection.DOWN;
    }
    void startMovementRight() {
        movement = true;
        dir=movementDirection.RIGHT;
    }
    void startMovementLeft() {
        movement = true;
        dir=movementDirection.LEFT;
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
    public void hit(){
        if(countHit==30 || countHit==0){
            countHit=0;
            hit=true;
        }
    }}












