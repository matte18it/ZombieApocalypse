package ZombieApocalypse.Model;

import java.awt.*;

public class Character {

    int x ;
    int y ;
    final int maxHealth=6;
    public int countHit;
    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    public movementDirection dir=movementDirection.RIGHT;   //Direzione iniziale
    int health;
    int speed=1;
    boolean movement = false;
    boolean hit=false;
    public int wight;
    public int height;
    public int centerX;
    public int centerY;

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












