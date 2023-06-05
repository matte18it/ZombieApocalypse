package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;

import java.util.Random;

public class FatZombie extends Enemy{

    public FatZombie(int x, int y) {
        super(x, y);
        wight= Settings.CELL_SIZEX+(Settings.CELL_SIZEX/2);
        height=Settings.CELL_SIZEY+(Settings.CELL_SIZEY/2);
        type= Enemies.EnemiesType.FATZOMBIE;
        healt=25;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    public boolean update() {
        if(healt<=0 && !dying){
            dying=true;
            int c=m.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return false;
            }

        if(Game.getInstance().getBackMenu()){
            stopAll=true;
            return false;
        }
        if(hit){
            if(countHit<30){
                countHit++;}
            else
                stopHit();
        }
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
        } else
            isMoving=false;




        return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isGround0(x+wight+10, y) && Game.getInstance().getWorld().isPlayer(x+10, y, centerX, centerY)){
            x=x+10;
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isGround0(x-10, y) && Game.getInstance().getWorld().isPlayer(x-10, y, centerX, centerY)){
            x=x-10;
            isMoving=true;
            dir=Settings.movementDirection.LEFT;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isGround0(x, y+height+10) && Game.getInstance().getWorld().isPlayer(x, y+10, centerX, centerY)){
            y=y+10;
            isMoving=true;
            dir=Settings.movementDirection.DOWN;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isGround0(x, y-10) && Game.getInstance().getWorld().isPlayer(x, y-10, centerX, centerY)){
            y=y-10;
            isMoving=true;
        dir=Settings.movementDirection.UP;}

    }
}
