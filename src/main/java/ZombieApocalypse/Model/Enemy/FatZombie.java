package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
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
    int countRun=0;

    public boolean update() {
        Point p=Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);
        //Gestione della Morte
        if(healt<=0 && !dying){
            dying=true;
            int c=m.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return false;
            }

        //Gestione della Pausa del gioco
        if(Game.getInstance().getBackMenu()){
            stopAll=true;
            return false;
        }
        //Gestione delle Hit
        if(hit){
            if(countHit<30){
                countHit++;}
            else
                stopHit();
        }
        //Gestione delle hit al Player
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();


        if(p.distance(turret)<200){
            if(countRun==0) {
                countRun++;
                if(p.y>=y && p.y<=y+height && p.x<turret.x)
                    moveLeft();
                if(p.y>=y && p.y<=y+height && p.x>=turret.x)
                    moveRight();
                if(p.x>=x && p.x<=x+height && p.y>=turret.y)
                    moveDown();
                if(p.x>=x && p.x<=x+wight && p.y<turret.y)
                    moveUp();

            }}
        if(countRun>0 && countRun<100){
            countRun++;
            switch (dir){
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case DOWN -> moveDown();
                case UP -> moveUp();
            }
        }
        if(countRun==100){
            countRun=0;
            isMoving=false;
        }




        return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+10, y) && Game.getInstance().getWorld().isPlayer(x+10, y, centerX, centerY)){
            x=x+5;
            isMoving=true;
            hitBox.x=x;

            dir=Settings.movementDirection.RIGHT;}else {isMoving=false; countRun=0;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-10, y) && Game.getInstance().getWorld().isPlayer(x-10, y, centerX, centerY)){
            x=x-5;
            isMoving=true;
            hitBox.x=x;

            dir=Settings.movementDirection.LEFT;}else {isMoving=false; countRun=0;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x, y+height+10) && Game.getInstance().getWorld().isPlayer(x, y+10, centerX, centerY)){
            y=y+5;
            hitBox.y=y;

            isMoving=true;
            dir=Settings.movementDirection.DOWN;}else {isMoving=false; countRun=0;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-10) && Game.getInstance().getWorld().isPlayer(x, y-10, centerX, centerY)){
            y=y-5;
            isMoving=true;
            hitBox.y=y;

            dir=Settings.movementDirection.UP;}else {isMoving=false; countRun=0;}

    }
}
