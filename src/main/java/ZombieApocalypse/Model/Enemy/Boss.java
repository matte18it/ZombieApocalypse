package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;

import java.util.Random;

public class Boss extends Enemy{

    public Boss(int x, int y) {
        super(x, y);
        wight= Settings.CELL_SIZEX*4;
        height=Settings.CELL_SIZEY*4;
        type= Enemies.EnemiesType.BOSS;
        healt=50;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    public boolean update() {
        //Gestione della Morte

        if(healt<=0 && countDeath<=8){
            countDeath++;
            dying=true;
            return true;
            }

        if(countDeath>8)
            return true;

        //Gestione della Pausa del gioco
        if(Game.getInstance().getBackMenu()){
            stopAll=true;
            return false;
        }
        //Gestione delle Hit
        if(hit){
            if(countHit<10){
                countHit++;}
            else
                stopHit();
        }
        //Gestione delle hit al Player
        //if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
          //  Game.getInstance().getPlayerCharacter().hit();

        //Parte di IA da implementare
       /* int f=m.nextInt(0,100);
        if(f<20){
        int i=m.nextInt(0,4);
        switch (i){
            case 0 ->{moveUp();}
            case 1 ->{moveDown();}
            case 2 ->{moveLeft();}
            case 3 ->{moveRight();}
        }
        //Aggiornamento ad ogni clock delle hitbox
        hitBox.x=x;
        hitBox.y=y;
        } else
            isMoving=false;  //Fine delle animazioni di movimento

*/


        return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isGround0(x+wight+20, y) && Game.getInstance().getWorld().isPlayer(x+20, y, centerX, centerY)){
            x=x+20;
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isGround0(x-20, y) && Game.getInstance().getWorld().isPlayer(x-20, y, centerX, centerY)){
            x=x-20;
            isMoving=true;
            dir=Settings.movementDirection.LEFT;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isGround0(x, y+height+20) && Game.getInstance().getWorld().isPlayer(x, y+20, centerX, centerY)){
            y=y+20;
            isMoving=true;
            dir=Settings.movementDirection.DOWN;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isGround0(x, y-20) && Game.getInstance().getWorld().isPlayer(x, y-20, centerX, centerY)){
            y=y-20;
            isMoving=true;
        dir=Settings.movementDirection.UP;}

    }
}
