package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;

public class Bandit extends Enemy{

    public Bandit(int x, int y) {
        super(x, y);
        wight= Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        type= Enemies.EnemiesType.BANDIT;
        healt=6;
        super.setSize();
    }

    //Per prova
    Random m=new Random();
    int count=0;
    public boolean update() {
        //Gestione della Morte
        if(healt<=0 && !dying){
            dying=true;
            int c=m.nextInt(4,9);
            Items.getInstance().dropItem(x,y, Items.ItemType.values()[c]);
            return false;
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
         Point player = Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);


        //Parte di Intelligenza artificiale da Implementare
        if(player.distance(turret)<300)
            shoot();
        int f=m.nextInt(0,100);
        if(f<20){
        int i=m.nextInt(0,4);
        switch (i){
            case 0 ->{moveUp();}
            case 1 ->{moveDown();}
            case 2 ->{moveLeft();}
            case 3 ->{moveRight();}
        }  }else
            isMoving=false;

        //Aggiornamento della hitbox necessario ad ogni clock
        hitBox.x=x;
        hitBox.y=y;




        return true;}


    private void shoot() {
        if(count==20){
            switch (dir){
                case RIGHT -> Bullets.getInstance().BulletBandit(x+wight+2,y+5, 10, 0, Bullet.Direction.RIGHT);
                case DOWN ->  Bullets.getInstance().BulletBandit(x+wight-5,y+height, 10, 0, Bullet.Direction.DOWN);
                case LEFT -> Bullets.getInstance().BulletBandit(x-10,y+5, 10, 0, Bullet.Direction.LEFT);
                case UP -> Bullets.getInstance().BulletBandit(x-2,y, 10, 0, Bullet.Direction.UP);

            } count=0;}
        count++;
        }


    public void updateGunPosition() {
        switch (dir){
            case RIGHT -> {gunPosition.x=x+wight-7; gunPosition.y=y+5;}
            case UP -> {gunPosition.x=x-10; gunPosition.y=y-5;}
            case LEFT -> {gunPosition.x=x-18; gunPosition.y=y+5;}
            case DOWN -> {gunPosition.x=x+centerX+5; gunPosition.y=y+centerY;}
        }

    }

    private void moveRight() {
        if(Game.getInstance().getWorld().isWalkable(x+wight+10, y) && Game.getInstance().getWorld().isPlayer(x+10, y, centerX, centerY)){
            x=x+10;
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;}
    }

    private void moveLeft() {
        if(Game.getInstance().getWorld().isWalkable(x-10, y) && Game.getInstance().getWorld().isPlayer(x-10, y, centerX, centerY)){
            x=x-10;
            isMoving=true;
            dir=Settings.movementDirection.LEFT;}
    }

    private void moveDown() {
        if(Game.getInstance().getWorld().isWalkable(x, y+height+10) && Game.getInstance().getWorld().isPlayer(x, y+10, centerX, centerY)){
            //y=y+10;
            //isMoving=true;
            dir=Settings.movementDirection.DOWN;}
    }

    private void moveUp() {
        if(Game.getInstance().getWorld().isWalkable(x, y-10) && Game.getInstance().getWorld().isPlayer(x, y-10, centerX, centerY)){
            y=y-10;
            isMoving=true;
        dir=Settings.movementDirection.UP;}

    }
}
