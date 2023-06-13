package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.*;
import ZombieApocalypse.View.GameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PlayerCharacter  {
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    //Gestisce il player e i suoi movimenti
    int x ;
    boolean muovo = true, muovo1 = true;
    int y ;
    final int maxHealth=6;
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




    PlayerCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        centerX=wight/2;
        centerY=height/2;

        health=6;
        hitBox=new Rectangle(x,y,wight,height);
    }


    public void hit() {
        if(countHit==0){
            if(GameData.sound)
                PlayWav.getInstance().playHurt1Sound();
            hit=true;
            if(health>0){
                health--;
                Game.getInstance().getMenuBar().removeHeart();

            }
        }
    }

    public void cure(){
        if(health<maxHealth){
            health++;
        Game.getInstance().getMenuBar().addHeart();}

    }
    public void speedUp(){
        if(countSpeed==300){
            speedUp=true;
            speed=3;}
        else
            countSpeed=countSpeed+300;

    }
    public void stopSpeed(){
        speedUp=false;
        countSpeed=300;
        speed=1;
    }
    public void addSpeed(){
        countSpeed--;
        if(countSpeed==0)
            stopSpeed();
    }
    public boolean speedUp=false;
    public int countSpeed=300;


    public void move() {
        soundWalk();

        if(dir==Settings.movementDirection.RIGHT && Game.getInstance().getWorld().isWalkable(getX()+wight+(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()+(10*speed), getY(), centerX, centerY) )
            x += (10*speed);
        else if(dir==Settings.movementDirection.LEFT && Game.getInstance().getWorld().isWalkable(getX()-(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()-(10*speed), getY(),centerX,centerY))
            x -= (10*speed);
        else if(dir== Settings.movementDirection.UP && Game.getInstance().getWorld().isWalkable(getX(), getY()-(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()-(10*speed),centerX,centerY))
            y -= (10*speed);
        else if(dir==Settings.movementDirection.DOWN && Game.getInstance().getWorld().isWalkable(getX(), getY()+height+(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()+(10*speed),centerX,centerY))
            y += (10*speed);
        else
            movement=false;


        hitBox.x=x;
        hitBox.y=y;
    }
    private void soundWalk() {
        if(GameData.sound && muovo) {
            muovo = false;
            if(muovo1){
                muovo1 = false;
                PlayWav.getInstance().playWalkSound();
            }
            else
                muovo1 = true;
        }
        else{
            muovo = true;
        }
    }

    public void chooseCoordinate() {
        Point p=Game.getInstance().getWorld().selectPlayerPosition();
        x=p.x;
        y=p.y;
    }
}

