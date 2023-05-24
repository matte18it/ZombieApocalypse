package ZombieApocalypse.Model;

public class Character {

    int x ;
    int y ;
    final int maxHealth=6;



    int getMaxHealth(){
        return maxHealth;
    }
     public int getX() {
        return x;
    }

     public int getY() {
        return y;
    }


    int money=0;
    int health=6;
    boolean movement = false;
    boolean hit=false;
    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;
}
