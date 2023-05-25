package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

import java.awt.*;

public class Character {

    static int x ;
    static int y ;
    final int maxHealth=6;
    public Rectangle hitBox;
    int wight;
    int height;
    int speed;
    int centerX=wight/2;
    int centerY=height/2;



    int getMaxHealth(){
        return maxHealth;
    }
     public static int getX() {
        return x;
    }

     public static int getY() {
        return y;
    }


    int money=0;
    int health=6;
    boolean movement = false;
    boolean hit=false;



    public enum movementDirection{RIGHT, LEFT, UP, DOWN};
    movementDirection dir;
}
