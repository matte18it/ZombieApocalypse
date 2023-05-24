package ZombieApocalypse.Model;

public class Enemy {
    private int x = 20;
    private int y = 20;
    private int speed = 1;
    private int maxHealth = 4;

    private boolean sound = false;

    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;

    public int getX(){return x;}
    public int getY(){return getY();}


}
