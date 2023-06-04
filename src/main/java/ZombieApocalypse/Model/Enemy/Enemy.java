package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Enemy.EnemyView;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  abstract class Enemy {
    int centerX, centerY;

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

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
    EnemyView view;
    Enemies.EnemiesType type;
    Rectangle hitBox;
    public EnemyView getView(){
        return view;
    }

    public Enemy(int x, int y){
        this.x=x;
        this.y=y;
    }
    public abstract boolean update();

    protected void setSize() {
        centerX=wight/2;
        centerY=height/2;
        hitBox=new Rectangle(x,y,wight, height);
        view=new EnemyView(type);

    }
}
