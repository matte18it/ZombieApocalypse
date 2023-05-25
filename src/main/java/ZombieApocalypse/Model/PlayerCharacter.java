package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;
import ZombieApocalypse.View.MenuBarView;

import java.awt.*;


public class PlayerCharacter extends Character{
    //Gestisce il player e i suoi movimenti

    private boolean sound=false;
    private final World world=new World();


    Audio audio = new Audio();

    // Metodo usato per musica in loop, riceve come argomento index da array Audio e lo passa a SetFile
    public void playMusic(int i) {

        audio.setFile(i);
        audio.play();
        audio.loop();
    }
    public void stopMusic() {
        audio.stop();
    }
    // Metodo usato per i sound effects
    public void playSE(int i) {
        audio.setFile(i);
        audio.play();
    }

    PlayerCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        x=50;
        y=50;
        health=6;
        hitBox= new Rectangle(x, y, Settings.CELL_SIZEX, Settings.CELL_SIZEY);
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

public int countHit;
    public void hit(){
        if(countHit==30 || countHit==0){
            countHit=0;
            hit=true;
            health--;
            MenuBarView.lifeUpdate(false);
        }


    }
    public void cure(){
        health++;
        MenuBarView.lifeUpdate(true);
    }


    public void move() {

        if(dir==movementDirection.RIGHT && (world.isGround0(getX()+Settings.CELL_SIZEX, getY())) && !world.isEnemy(getX()+20, getY()))
            x += 10;
        else if(dir==movementDirection.LEFT && (world.isGround0(getX()-Settings.CELL_SIZEX, getY())) && !world.isEnemy(getX()-20, getY()))
            x -= 10;
        else if(dir==movementDirection.UP && (world.isGround0(getX(), getY()-Settings.CELL_SIZEY)) && !world.isEnemy(getX(), getY()-20) )
            y -= 10;
        else if(dir==movementDirection.DOWN && (world.isGround0(getX(), getY()+Settings.CELL_SIZEY))&& !world.isEnemy(getX(), getY()+20))
            y += 10;
        else
            movement=false;
        if(sound){
            playSE(0);
            sound=false;}
        else
            sound=true;
        hitBox.x=x;
        hitBox.y=y;



    }


}
