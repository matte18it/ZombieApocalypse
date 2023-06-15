package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import ZombieApocalypse.Utility.Settings;
import java.awt.*;

public class BombBandit extends Enemy{
    BombBandit(int x, int y) {super(x,y, Enemies.EnemiesType.BOMBBANDIT);}
    private int count=0;
    public boolean update() {
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0 ){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.BOMBBANDIT);
            dying=true;
            Items.getInstance().enemyDrop(x,y);
            return true;
            }
        //Gestione dalla Pausa del Gioco
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
        //Gestione dei Pattern
        Point player= Game.getInstance().getPlayer().getPosition();
        Point enemy = getEnemyPosition();
        //2 pattern che si scambiamo a seconda della distanza con il player
        //Pattern 1
        if(player.distance(enemy)<300){
            isMoving=false;
            if(player.y>=y && player.y<=y+height && player.x<enemy.x)
                dir= Settings.movementDirection.LEFT;
            if(player.y>=y && player.y<=y+height && player.x>=enemy.x)
                dir= Settings.movementDirection.RIGHT;
            if(player.x>=x && player.x<=x+height && player.y>=enemy.y)
                dir= Settings.movementDirection.DOWN;
            if(player.x>=x && player.x<=x+wight && player.y<enemy.y)
                dir= Settings.movementDirection.UP;
            shoot(player, enemy);
            //Pattern 2
        }else{
        int f=random.nextInt(0,100);
        if(f<20){
        int i=random.nextInt(0,4);
        switch (i){
            case 0 ->moveUp();
            case 1 ->moveDown();
            case 2 ->moveLeft();
            case 3 ->moveRight();
        }  }else
            isMoving=false;}
        return true;}
    private void shoot(Point player, Point enemy) {
        //Calcola la distanza del lancio della Granata
        int totalFrame = (int) player.distance(enemy);
        //Lancia la granata ogni 40 frame
        if(count==40){
            switch (dir){
                case RIGHT -> Bullets.getInstance().BulletGrenadeBandit(x+wight+2,y+5, Bullet.Direction.RIGHT, totalFrame);
                case DOWN ->  Bullets.getInstance().BulletGrenadeBandit(x+wight-5,y+height,  Bullet.Direction.DOWN, totalFrame);
                case LEFT -> Bullets.getInstance().BulletGrenadeBandit(x-10,y+5, Bullet.Direction.LEFT, totalFrame);
                case UP -> Bullets.getInstance().BulletGrenadeBandit(x-2,y, Bullet.Direction.UP, totalFrame);

            } count=0;}
        count++;
        }
}
