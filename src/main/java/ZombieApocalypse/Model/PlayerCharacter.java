package ZombieApocalypse.Model;
import ZombieApocalypse.Utility.*;
import java.awt.*;
 public  class PlayerCharacter  {
    //Gestisce il player e i suoi movimenti
    int x ;
    int y ;
    final int wight;
    final int height;
    final int centerX;
    final int centerY;
     Rectangle hitBox;
    //Vita e massima vita
    final int maxHealth=6;
    int health;
    //gestione del suono
    boolean muovo = true, muovo1 = true;
     int countHit;
     boolean speedUp=false;
     int countSpeed=300;
     boolean movement = false;
     boolean hit=false;
     int speed=1;
     PlayerCharacter(){
         wight=Settings.CELL_SIZEX;
         height=Settings.CELL_SIZEY;
         centerX=wight/2;
         centerY=height/2;
         health=6;
         hitBox=new Rectangle(x,y,wight,height);
     }
     //Decide le coordinate iniziale del player
     void chooseCoordinate() {
         Point p=Game.getInstance().selectPlayerSpawn();
         x=p.x;
         y=p.y;
     }
    //Gestione delle hit
     void addHit() {
        countHit++;
        if(countHit==30) {
            stopHit();
        }
    }
    Settings.movementDirection dir= Settings.movementDirection.RIGHT;   //Direzione iniziale
    //Gestione della pozione di velocità
     void speedUp(){
        if(countSpeed==300){
            speedUp=true;
            speed=3;}
        else
            countSpeed=countSpeed+300;

    }
     void stopSpeed(){
        speedUp=false;
        countSpeed=300;
        speed=1;
    }
     void addSpeed(){
        countSpeed--;
        if(countSpeed==0)
            stopSpeed();
    }

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
    //Gestione delle hit
    void hit() {
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
     void stopHit() {
        hit=false;
        countHit=0;
    }
     //Gestione della cura
     void cure(){
        if(health<maxHealth){
            health++;
        Game.getInstance().getMenuBar().addHeart();}

    }
    //Aggiornamento dei movimenti del player
    void move() {
        soundWalk();
        if(dir==Settings.movementDirection.RIGHT && Game.getInstance().isWorldWalkable(x+wight+(10*speed), y) && Game.getInstance().isEnemy(x+(10*speed), y, centerX, centerY) )
            x += (10*speed);
        else if(dir==Settings.movementDirection.LEFT && Game.getInstance().isWorldWalkable(x-(10*speed), y) && Game.getInstance().isEnemy(x-(10*speed), y,centerX,centerY))
            x -= (10*speed);
        else if(dir== Settings.movementDirection.UP && Game.getInstance().isWorldWalkable(x, y-(10*speed)) && Game.getInstance().isEnemy(x, y-(10*speed),centerX,centerY))
            y -= (10*speed);
        else if(dir==Settings.movementDirection.DOWN && Game.getInstance().isWorldWalkable(x, y+height+(10*speed)) && Game.getInstance().isEnemy(x, y+(10*speed),centerX,centerY))
            y += (10*speed);
        else
            movement=false;
        hitBox.x=x;
        hitBox.y=y;
    }
    //Gestione del Suono
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


}

