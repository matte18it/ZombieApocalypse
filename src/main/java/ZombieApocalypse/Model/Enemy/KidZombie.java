package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import java.awt.*;
public class KidZombie extends Enemy{

     KidZombie(int x, int y) {super(x,y, Enemies.EnemiesType.KIDZOMBIE);}
    public boolean update() {
        Point p=getPlayerPosition();
        Point enemy = getEnemyPosition();
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.KIDZOMBIE);
            dying=true;
            Items.getInstance().enemyDrop(x,y);
            return true;
            }
        //Gestione della Pausa del gioco e del Menu
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
        //Gestione dei Pattern
        if(p.distance(enemy)<200){
            if(enemy.x>p.x)
                moveLeft();
            else
                moveRight();
            if(enemy.y>p.y)
                moveUp();
            else
                moveDown();
        }else{
        int i=random.nextInt(0,4);
        switch (i){
            case 0 ->moveUp();
            case 1 ->moveDown();
            case 2 ->moveLeft();
            case 3 ->moveRight();
        }}
        return true;
    }
}
