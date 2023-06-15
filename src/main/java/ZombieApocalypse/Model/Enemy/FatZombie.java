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
        Point p=Game.getInstance().getPlayer().getPosition();
        Point enemy = getEnemyPosition();
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.FATZOMBIE);
            dying=true;
            Items.getInstance().enemyDrop(x,y);
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
        checkHitBox();
        //Gestione del pattern
        if(p.distance(enemy)<250){
            if(countRun==0) {
                countRun++;
                if(p.y>=y && p.y<=y+height && p.x<enemy.x)
                    moveLeft();
                if(p.y>=y && p.y<=y+height && p.x>=enemy.x)
                    moveRight();
                if(p.x>=x && p.x<=x+height && p.y>=enemy.y)
                    moveDown();
                if(p.x>=x && p.x<=x+wight && p.y<enemy.y )
                    moveUp();
            }}
        //Gestione della corsa
        if(countRun>0 && countRun<50){
            countRun++;
            switch (dir){
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
                case DOWN -> moveDown();
                case UP -> moveUp();
            }
        }
        if(countRun==50){
            countRun=0;
            isMoving=false;
        }
        return true;
    }
}
