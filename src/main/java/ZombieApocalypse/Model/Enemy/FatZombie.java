package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import java.awt.*;
public class FatZombie extends Enemy{
    FatZombie(int x, int y) {super(x,y, Enemies.EnemiesType.FATZOMBIE);}
    //Gestione della corsa
    private int countRun=0;
    public boolean update() {
        Point p=getPlayerPosition();
        Point enemy = getEnemyPosition();
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.FATZOMBIE);
            dying=true;
            int c=random.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return true;
            }
        //Gestione della Pausa del gioco
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
        //Gestione delle hit al Player
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();
        //Gestione del pattern
        if(p.distance(enemy)<200){
            if(countRun==0) {
                countRun++;
                if(p.y>=y && p.y<=y+height && p.x<enemy.x)
                    moveLeft();
                if(p.y>=y && p.y<=y+height && p.x>=enemy.x)
                    moveRight();
                if(p.x>=x && p.x<=x+height && p.y>=enemy.y)
                    moveDown();
                if(p.x>=x && p.x<=x+wight && p.y<enemy.y)
                    moveUp();

            }}
        //Gestione della corsa
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
}
