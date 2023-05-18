package ZombieApocalypse.Model;

import java.awt.event.KeyEvent;

public class PlayerCharacter {
    //Gestisce il player e i suoi movimenti
    private int x = 0;
    private int y = 0;
    private boolean movement = false;
    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;

    private int jump = 0;

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


    public void move() {
        if(dir==movementDirection.RIGHT)
            x += 10;
        if(dir==movementDirection.LEFT)
            x -= 10;
        if(dir==movementDirection.UP)
            y -= 10;
        if(dir==movementDirection.DOWN)
            y += 10;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
