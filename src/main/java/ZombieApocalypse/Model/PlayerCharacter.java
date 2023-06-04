package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.Settings;

import java.awt.*;


public class PlayerCharacter  {
    //Gestisce il player e i suoi movimenti
    int x ;
    int y ;
    final int maxHealth=6;
    public int countHit;

    public void addHit() {
        countHit++;
        if(countHit==30) {
            stopHit();
        }
    }

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


    private boolean sound=false;
     final World world=new World();

    // Metodo usato per musica in loop, riceve come argomento index da array Audio e lo passa a SetFile

    PlayerCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        centerX=wight/2;
        centerY=height/2;
        x=50;
        y=50;
        health=6;
        hitBox=new Rectangle(x,y,wight,height);
    }


    public void hit() {
        if(countHit==0){
            hit=true;
            health--;
            Game.getInstance().getMenuBar().removeHeart();
        }
    }

    public void cure(){
        Game.getInstance().getMenuBar().addHeart();
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


        if(dir==movementDirection.RIGHT && world.isGround0(getX()+wight+(10*speed), getY()) && world.isEnemy(getX()+(10*speed), getY()) )
            x += (10*speed);
        else if(dir==movementDirection.LEFT && world.isGround0(getX()-(10*speed), getY()) && world.isEnemy(getX()-(10*speed), getY()))
            x -= (10*speed);
        else if(dir==movementDirection.UP && world.isGround0(getX(), getY()-(10*speed)) && world.isEnemy(getX(), getY()-(10*speed)))
            y -= (10*speed);
        else if(dir==movementDirection.DOWN && world.isGround0(getX(), getY()+height+(10*speed)) && world.isEnemy(getX(), getY()+(10*speed)))
            y += (10*speed);
        else
            movement=false;

        if(sound)  sound = false; else  sound = true;

        hitBox.x=x;
        hitBox.y=y;
    }
}

