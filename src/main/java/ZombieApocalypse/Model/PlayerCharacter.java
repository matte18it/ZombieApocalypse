package ZombieApocalypse.Model;
import ZombieApocalypse.Utility.*;
import java.awt.*;
 public  class PlayerCharacter  {
    //Gestisce il player e i suoi movimenti
    private int x ;
    private int y ;
    public final int wight;
    public final int height;
    public final int centerX;
    public final int centerY;
     private Rectangle hitBox;
    //Vita e massima vita
    public final int maxHealth=6;
    private int health;
    //gestione del suono
    private boolean muovo = true, muovo1 = true;
     private int countHit;
     private boolean speedUp=false;
     private int countSpeed=300;
     private boolean movement = false;
     private boolean hit=false;
     private int speed=1;
     private Settings.movementDirection dir= Settings.movementDirection.RIGHT;   //Direzione iniziale
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
         Point p=Game.getInstance().getWorld().selectPlayerPosition();
         x=p.x;
         y=p.y;
     }
    //Gestione delle hit
     public void addHit() {
        countHit++;
        if(countHit==30) {
            stopHit();
        }
    }
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

    public void startMovementUp() {
        movement = true;
        dir= Settings.movementDirection.UP;
    }
    public void startMovementDown() {
        movement = true;
        dir=Settings.movementDirection.DOWN;
    }
    public void startMovementRight() {
        movement = true;
        dir=Settings.movementDirection.RIGHT;
    }
    public void startMovementLeft() {
        movement = true;
        dir=Settings.movementDirection.LEFT;
    }

    public void stopMovement() {
        movement = false;
    }
    //Gestione delle hit
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
        if (dir == Settings.movementDirection.RIGHT && Game.getInstance().getWorld().isWalkable(x + wight + (10 * speed), y) && Game.getInstance().getWorld().isEnemy(x + (10 * speed), y, centerX, centerY)) {
            x += (10 * speed); soundWalk();
        } else if (dir == Settings.movementDirection.LEFT && Game.getInstance().getWorld().isWalkable(x - (10 * speed), y) && Game.getInstance().getWorld().isEnemy(x - (10 * speed), y, centerX, centerY)) {
            x -= (10 * speed); soundWalk();
        }else if(dir== Settings.movementDirection.UP && Game.getInstance().getWorld().isWalkable(x, y-(10*speed)) && Game.getInstance().getWorld().isEnemy(x, y-(10*speed),centerX,centerY)) {
            y -= (10 * speed); soundWalk();
        }else if(dir==Settings.movementDirection.DOWN && Game.getInstance().getWorld().isWalkable(x, y+height+(10*speed)) && Game.getInstance().getWorld().isEnemy(x, y+(10*speed),centerX,centerY)) {
            y += (10 * speed); soundWalk();
        }else
            movement=false;
        hitBox.x=x;
        hitBox.y=y;
    }
    //Getters
     public int getY() {return y;}
     public int getX() {return x;}
     public int getHealth(){return health;}
     public Rectangle getHitBox(){return hitBox;}
     public Settings.movementDirection getDir(){return dir;}
     public Point getPosition(){
         return new Point(x+centerX, y+centerY);
     }
     public boolean getMovement(){return movement;}
     public boolean getHit(){return hit;}
     public boolean getSpeedUp(){return speedUp;}
     public int getCountSpeed(){return countSpeed;}
     public int getCountHit(){return countHit;}


     //Gestione del Suono
    private void soundWalk() {
        if(GameData.sound && muovo) {
            muovo = false;
            if(muovo1){
                muovo1 = false;
                PlayWav.getInstance().playWalkSound(x,y);
            }
            else
                muovo1 = true;
        }
        else{
            muovo = true;
        }
    }


}

