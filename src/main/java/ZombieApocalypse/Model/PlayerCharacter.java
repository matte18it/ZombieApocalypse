package ZombieApocalypse.Model;

public class PlayerCharacter {
    //Gestisce il player e i suoi movimenti
    private int x = 0;
    private int y = 0;
    private boolean movement = false;
    private enum movementDirection{RIGHT, LEFT, UP, DOWN};
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
        x += 10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
