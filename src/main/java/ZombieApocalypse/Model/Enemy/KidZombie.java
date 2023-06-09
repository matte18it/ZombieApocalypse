package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

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
            if(GameData.sound)
                PlayWav.getInstance().playZombieHit();
            if(countHit<30){
                countHit++;}
            else
                stopHit();
        }
        //Gestione delle hit al Player
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();

        //Parte di Intelligenza artificiale da Implementare
        int f=m.nextInt(0,100);
        if(f<20){
        int i=m.nextInt(0,4);
        switch (i){
            case 0 ->{moveUp();}
            case 1 ->{moveDown();}
            case 2 ->{moveLeft();}
            case 3 ->{moveRight();}
        }

            //Aggiornamento della hitbox necessario ad ogni clock
            hitBox.x=x;
        hitBox.y=y;
        } else
            isMoving=false;//Stop delle animazioni di movimento



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
