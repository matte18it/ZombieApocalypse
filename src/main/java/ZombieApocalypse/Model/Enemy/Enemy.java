package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Enemy.EnemyView;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  class Enemy {
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

    public Enemy(int x, int y, Enemies.EnemiesType value){
        this.x=x;
        this.y=y;
        this.type=value;
        switch (type){
            case SKINNYZOMBIE -> {healt=6; wight= Settings.CELL_SIZEX; height=Settings.CELL_SIZEY;}
        }
        hitBox=new Rectangle(x,y,wight, height);
        view=new EnemyView(type);
        centerX=wight/2;
        centerY=height/2;

    }
    public boolean update(){
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();
        return false;
    };
}
