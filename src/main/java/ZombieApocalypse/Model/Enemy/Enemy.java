package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Enemy.BossView;
import ZombieApocalypse.View.Enemy.EnemyView;
import ZombieApocalypse.View.Enemy.EnemyViewInterface;

import java.awt.*;
import java.util.Random;

public  abstract class Enemy {
    //Gestione del movimento  e morte
    public boolean isMoving=false;
    public boolean dying=false;
    boolean run=false;
    //Utilizzo della random nei pattern
    final Random random =new Random();
    //Gestione dell'arma del Bandit
    Point gunPosition=new Point(0,0);
    //Gestione dei due attacchi del Boss
    public boolean attack1=false;
    public boolean attack2=false;
    public int countAttack=0;
    //Dati su vita, posizione size ecc
    int x;
    int y;
    int wight, height;
    int healt;
    public Settings.movementDirection dir= Settings.movementDirection.DOWN;
    int centerX, centerY;
    Rectangle hitBox;
    //Numero di pixel di spostamento che un nemico deve fare se chiamata la move
    int numStep;
    //Gestione della view e tipo di nemico
    EnemyViewInterface view;
    public Enemies.EnemiesType type;
    //Gestione delle hit
    public int countHit=0;
    public boolean hit=false;
    void gettingHit(int damage) {
        hit=true;
        healt=healt-damage;
    }
     void stopHit(){
        countHit=0;
        hit=false;
    }
    //gestione della morte
     public int countDeath=0;
    //Gestione dei movimenti del turretZombie
    public double turretCount=0;
    //Getters
     public int getX() {return x;}
     public int getY() {return y;}
    public int getWight() {return wight;}
    public int getHeight(){return height;}
    Point getPlayerPosition(){return Game.getInstance().getPlayerPosition();}
    Point getEnemyPosition(){return new Point(x + centerX, y + centerY);}
    public int getGunX() {return gunPosition.x;}
    public int getGunY() {return gunPosition.y;}
    public EnemyViewInterface getView(){return view;}
    public void updateGunPosition() {}
    //Set iniziale dei parametri
    Enemy(int x, int y, Enemies.EnemiesType enemiesType) {
        this.type=enemiesType;
        this.x=x;
        this.y=y;
        wight=Enemies.getInstance().getWight(type);
        height=Enemies.getInstance().getHeight(type);
        centerX=wight/2;
        centerY=height/2;
        switch (type){
            case BANDIT ->{ numStep=8; healt=6;}
            case BOMBBANDIT -> {numStep=10; healt=4;}
            case BOSS -> {numStep=4; healt=150;}
            case KIDZOMBIE ->{ numStep=15; healt=2;}
            case FATZOMBIE -> {numStep=5; healt=25;}
            case SKINNYZOMBIE -> {numStep=10; healt=5;}
            case TURRETZOMBIE -> {numStep=0; healt=5;}
            default -> {numStep=0; healt=0;}
        }
        hitBox=new Rectangle(x,y,wight, height);
        if(type== Enemies.EnemiesType.BOSS)
            view=new BossView( this);
        else
            view=new EnemyView(type, this);
    }
    //Gestione dei movimenti del nemico
     void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+numStep, y) && Enemies.getInstance().isPlayer(x + numStep, y,  type)){
            if(run)
                x=x+(numStep*3);
            else
                x=x+numStep;
            hitBox.x=x;
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;
        }else
            isMoving=false;
    }
     void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-numStep, y) && Enemies.getInstance().isPlayer(x - numStep, y,  type)){
            if(run)
                x=x-(numStep*3);
            else
                x=x-numStep;
            hitBox.x=x;
            isMoving=true;
            dir=Settings.movementDirection.LEFT;
        }else
            isMoving=false;
    }

     void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x, y+height+numStep) && Enemies.getInstance().isPlayer(x, y + numStep, type)){
            if(run)
                y=y+(numStep*3);
            else
                y=y+numStep;
            isMoving=true;
            hitBox.y=y;
            dir=Settings.movementDirection.DOWN;
        }else
            isMoving=false;
    }
     void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-numStep) && Enemies.getInstance().isPlayer(x, y - numStep,  type)){
            if(run)
                y=y-(numStep*3);
            else
                y=y-numStep;
            hitBox.y=y;
            isMoving=true;
            dir=Settings.movementDirection.UP;
        }else
            isMoving=false;
    }
    public abstract boolean update();

}
