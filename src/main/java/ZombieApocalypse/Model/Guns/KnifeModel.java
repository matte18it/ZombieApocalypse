package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;


public class KnifeModel extends GunModel{

    public KnifeModel(){
        super(Settings.CELL_SIZEX-5, Settings.CELL_SIZEY/2, 4);

        hitBox=new Rectangle(imagePosition.x, imagePosition.y, width,height);
    }
    private int countAttack=0;
    public void attack() {
        if(GameData.sound) {
            PlayWav.getInstance().playKnifeSound();
        }
        countAttack=0;
        attack=true;
        Enemies.getInstance().checkEnemiesHit(hitBox, damage);
    }

    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.DOWN){
            x= Game.getInstance().getPlayer().getX()+(Game.getInstance().getPlayer().wight/2)+5;
            y= Game.getInstance().getPlayer().getY()+ Game.getInstance().getPlayer().height-15;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            //Inversione per permettere al GraphicPanel di disegnare orizzondale e verticale
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP){
            x= Game.getInstance().getPlayer().getX()-1;
            y= Game.getInstance().getPlayer().getY()-18;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.LEFT){
            x= Game.getInstance().getPlayer().getX()- Game.getInstance().getPlayer().wight+12;
            y= Game.getInstance().getPlayer().getY()+(Game.getInstance().getPlayer().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.RIGHT){
            x= Game.getInstance().getPlayer().getX()+ Game.getInstance().getPlayer().wight-5;
            y= Game.getInstance().getPlayer().getY()+(Game.getInstance().getPlayer().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }}

    public void updateAttack() { //Gestione delle animazioni di attacco
        countAttack++;
    }
    public int getCountAttack(){return countAttack;}
}


