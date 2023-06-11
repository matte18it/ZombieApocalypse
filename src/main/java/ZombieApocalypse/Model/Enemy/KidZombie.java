package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;

public class KidZombie extends Enemy{

    public KidZombie(int x, int y) {
        super(x, y);
        wight= (Settings.CELL_SIZEX/2)+10;
        height=(Settings.CELL_SIZEY/2)+10;
        type= Enemies.EnemiesType.KIDZOMBIE;
        healt=4;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    public boolean update() {
        Point p=Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);
        if(dying)
            return false;

        //Gestione della Morte
        if(healt<=0){
            dying=true;
            int c=m.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return true;
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
            if(turret.x>p.x)
                moveLeft();
            else
                moveRight();
            if(turret.y>p.y)
                moveUp();
            else
                moveDown();


        }else{


        int i=m.nextInt(0,4);
        switch (i){
            case 0 ->{moveUp();}
            case 1 ->{moveDown();}
            case 2 ->{moveLeft();}
            case 3 ->{moveRight();}
        }}

            return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+10, y) && Game.getInstance().getWorld().isPlayer(x+10, y, centerX, centerY)){
            x=x+15;
            isMoving=true;
            hitBox.x=x;
            dir=Settings.movementDirection.RIGHT;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-10, y) && Game.getInstance().getWorld().isPlayer(x-10, y, centerX, centerY)){
            x=x-15;
            isMoving=true;
            hitBox.x=x;
            dir=Settings.movementDirection.LEFT;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x, y+height+10) && Game.getInstance().getWorld().isPlayer(x, y+10, centerX, centerY)){
            y=y+15;
            isMoving=true;
            hitBox.y=y;
            dir=Settings.movementDirection.DOWN;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-10) && Game.getInstance().getWorld().isPlayer(x, y-10, centerX, centerY)){
            y=y-15;
            isMoving=true;
            hitBox.y=y;
        dir=Settings.movementDirection.UP;}

    }
}
