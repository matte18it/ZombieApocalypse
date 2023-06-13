package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Enemy.BossView;
import ZombieApocalypse.View.Enemy.EnemyView;
import ZombieApocalypse.View.Enemy.EnemyViewInterface;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  abstract class Enemy {
    public boolean isMoving=false;
    public Settings.movementDirection dir= Settings.movementDirection.DOWN;
    int centerX, centerY;

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
    public boolean attack1=false;
    public boolean attack2=false;
    public int countAttack=0;

    int x;
    int y;
    int wight, height;
    int healt;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHealt(){return healt;}

    public int getWight() {
        return wight;
    }
    public int getHeight(){return height;}
    EnemyViewInterface view;
    public Enemies.EnemiesType type;
    Rectangle hitBox;
    public EnemyViewInterface getView(){
        return view;
    }

    public Enemy(int x, int y){
        this.x=x;
        this.y=y;
    }
    public abstract boolean update();

    protected void setSize() {
        wight=Enemies.getInstance().getWight(type);
        height=Enemies.getInstance().getHeight(type);
        centerX=wight/2;
        centerY=height/2;
        hitBox=new Rectangle(x,y,wight, height);
        if(type== Enemies.EnemiesType.BOSS)
            view=new BossView( this);
        else
            view=new EnemyView(type, this);

    }
    public int countHit=0;
    public boolean hit=false;
    public int countDeath=0;
    public double turretCount=0;

    public void gettingHit(int damage) {
            hit=true;
            healt=healt-damage;
    }
    public void stopHit(){
        countHit=0;
        hit=false;
    }
    public boolean stopAll=false;

    public boolean dying=false;
    Point gunPosition=new Point(0,0);

    public int getGunX() {
        return gunPosition.x;
    }
    public int getGunY() {
        return gunPosition.y;
    }

    public void updateGunPosition() {
    }
}
