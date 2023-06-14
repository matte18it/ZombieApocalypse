package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;


public class SkinnyZombie extends Enemy{

    SkinnyZombie(int x, int y) {super(x,y, Enemies.EnemiesType.SKINNYZOMBIE);}
    public boolean update() {
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0 ){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.SKINNYZOMBIE);
            dying=true;
            Items.getInstance().enemyDrop(x,y);
            return true;
            }
        //Gestione dalla Pausa del Gioco e del Menu
        if(Game.getInstance().getBackMenu()){
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

        //Gestione del pattern: Movimenti Random
        int f=random.nextInt(0,100);
        if(f<20){
        int i=random.nextInt(0,4);
        switch (i){
            case 0 ->moveUp();
            case 1 ->moveDown();
            case 2 ->moveLeft();
            case 3 ->moveRight();
        }
        } else
            isMoving=false;  //Stop delle animazioni di movimento
        return true;
    }
}
