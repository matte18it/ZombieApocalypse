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
    public void attack() {
        if(GameData.sound) {
            PlayWav.getInstance().playKnifeSound();
        }
        attack=true;
        Enemies.getInstance().checkEnemiesHit(hitBox, damage);
    }

    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.DOWN){
            x= Game.getInstance().getPlayerX()+(Game.getInstance().getPlayerWight()/2)+5;
            y= Game.getInstance().getPlayerY()+ Game.getInstance().getPlayerHeight()-15;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            //Inversione per permettere al GraphicPanel di disegnare orizzondale e verticale
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.UP){
            x= Game.getInstance().getPlayerX()-1;
            y= Game.getInstance().getPlayerY()-18;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.LEFT){
            x= Game.getInstance().getPlayerX()- Game.getInstance().getPlayerWight()+12;
            y= Game.getInstance().getPlayerY()+(Game.getInstance().getPlayerHeight()/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }
        if(Game.getInstance().getPlayerDirection()== Settings.movementDirection.RIGHT){
            x= Game.getInstance().getPlayerX()+ Game.getInstance().getPlayerWight()-5;
            y= Game.getInstance().getPlayerY()+(Game.getInstance().getPlayerHeight()/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }} }


