package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;

public class BombBandit extends Enemy{
    int totalFrame;

    public BombBandit(int x, int y) {
        super(x, y);
        wight= Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        type= Enemies.EnemiesType.BOMBBANDIT;
        healt=4;
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
        Point player = Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);
        totalFrame= (int) player.distance(turret);
        //Lancia la granata ogni 30 frame

        if(count==40){
            switch (dir){
                case RIGHT -> Bullets.getInstance().BulletGrenadeBandit(x+wight+2,y+5, Game.getInstance().getGrenadeModel().getWidth(), 0, Bullet.Direction.RIGHT, totalFrame);
                case DOWN ->  Bullets.getInstance().BulletGrenadeBandit(x+wight-5,y+height, Game.getInstance().getGrenadeModel().getWidth(), 0, Bullet.Direction.DOWN, totalFrame);
                case LEFT -> Bullets.getInstance().BulletGrenadeBandit(x-10,y+5, Game.getInstance().getGrenadeModel().getWidth(), 0, Bullet.Direction.LEFT, totalFrame);
                case UP -> Bullets.getInstance().BulletGrenadeBandit(x-2,y, Game.getInstance().getGrenadeModel().getWidth(), 0, Bullet.Direction.UP, totalFrame);

            } count=0;}
        count++;
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
