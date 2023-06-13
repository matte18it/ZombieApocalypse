package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.util.Random;

public class SkinnyZombie extends Enemy{

    public SkinnyZombie(int x, int y) {
        super(x, y);

        type= Enemies.EnemiesType.SKINNYZOMBIE;
        healt=6;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    public boolean update() {
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0 ){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.SKINNYZOMBIE);
            dying=true;
            int c=m.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return true;
            }
        //Gestione dalla Pausa del Gioco
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
        //Gestione delle hit con il Player
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();

        //Movimenti Random
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
        } else
            isMoving=false;  //Stop delle animazioni di movimento



        return true;
    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+10, y) && Enemies.getInstance().isPlayer(x+10, y, type)){
            x=x+10;
            isMoving=true;
            hitBox.x=x;
            dir=Settings.movementDirection.RIGHT;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-10, y) && Enemies.getInstance().isPlayer(x-10, y,  type)){
            x=x-10;
            isMoving=true;
            hitBox.x=x;
            dir=Settings.movementDirection.LEFT;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x, y+height+10) && Enemies.getInstance().isPlayer(x, y + 10,  type)){
            y=y+10;
            isMoving=true;
            hitBox.y=y;
            dir=Settings.movementDirection.DOWN;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-10) && Enemies.getInstance().isPlayer(x, y-10, type)){
            y=y-10;
            isMoving=true;
            hitBox.y=y;
        dir=Settings.movementDirection.UP;}

    }
}
