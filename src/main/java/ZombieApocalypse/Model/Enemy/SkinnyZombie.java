package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.util.Random;

public class SkinnyZombie extends Enemy{

    public SkinnyZombie(int x, int y) {
        super(x, y);
        wight= Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        type= Enemies.EnemiesType.SKINNYZOMBIE;
        healt=6;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();
        int f=m.nextInt(0,100);
        if(f<20){
        int i=m.nextInt(0,4);
        switch (i){
            case 0 ->{moveUp();}
            case 1 ->{moveDown();}
            case 2 ->{moveLeft();}
            case 3 ->{moveRight();}
        }
        hitBox.x=x;
        hitBox.y=y;
        }


        return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isGround0(x+wight+10, y)){
            x=x+10;
            view.isMoving(Settings.movementDirection.RIGHT);}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isGround0(x-10, y)){
            x=x-10;
            view.isMoving(Settings.movementDirection.LEFT);}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isGround0(x, y+height+10)){
            y=y+10;
            view.isMoving(Settings.movementDirection.DOWN);}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isGround0(x, y-10)){
            y=y-10;
        view.isMoving(Settings.movementDirection.UP);}

    }
}
