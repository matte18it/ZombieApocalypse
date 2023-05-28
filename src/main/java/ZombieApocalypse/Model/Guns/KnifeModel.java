package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class KnifeModel extends GunModel{

    public KnifeModel(){
        width= Settings.CELL_SIZEX-5;
        height=Settings.CELL_SIZEY/2;
        imagePosition=new Point(0,0);
        hitBox=new Rectangle(imagePosition.x, imagePosition.y, width,height);
        damage=1;

    }
    public void attack() {
        attack=true;
        if(hitBox.intersects(Game.getInstance().getEnemyCharacter().hitBox)){
            Game.getInstance().getEnemyCharacter().hit();
        }
    }
    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.DOWN){
            x=Game.getInstance().getPlayerCharacter().getX()+(Game.getInstance().getPlayerCharacter().wight/2)+5;
            y=Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().height-15;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.UP){
            x=Game.getInstance().getPlayerCharacter().getX()-1;
            y=Game.getInstance().getPlayerCharacter().getY()-18;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.LEFT){
            x=Game.getInstance().getPlayerCharacter().getX()-Game.getInstance().getPlayerCharacter().wight+12;
            y=Game.getInstance().getPlayerCharacter().getY()+(Game.getInstance().getPlayerCharacter().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.RIGHT){
            x=Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().wight-5;
            y=Game.getInstance().getPlayerCharacter().getY()+(Game.getInstance().getPlayerCharacter().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }

    }

    public boolean isUp(){
        return Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.DOWN;

    }}
