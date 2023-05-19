package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

import java.awt.event.KeyEvent;

public class PlayerCharacter {
    //Gestisce il player e i suoi movimenti
    private int x = 20;
    private int y = 20;
    int money=0;
    int health=10;
    boolean movement = false;
    boolean hit=true;  //prova
    private final World world=new World();
    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;



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


    public void move() {
        if(dir==movementDirection.RIGHT && world.isValidCoordinate(getX()+10, getY()))
            x += 10;
        else if(dir==movementDirection.LEFT && world.isValidCoordinate(getX()-10, getY()))
            x -= 10;
        else if(dir==movementDirection.UP && world.isValidCoordinate(getX(), getY()-10))
            y -= 10;
        else if(dir==movementDirection.DOWN && world.isValidCoordinate(getX(), getY()+10))
            y += 10;
        else
            movement=false;


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
