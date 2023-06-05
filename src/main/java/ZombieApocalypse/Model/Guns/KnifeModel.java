package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
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
        if(GameData.sound) {
            PlayWav.getInstance().loadSound("/Audio/KnifeSound.wav");
            PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
            PlayWav.getInstance().playSound();
        }

        attack=true;
        Enemies.getInstance().checkHitBox(hitBox, damage);

        }

    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.DOWN){
            x=Game.getInstance().getPlayerCharacter().getX()+(Game.getInstance().getPlayerCharacter().wight/2)+5;
            y=Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().height-15;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.UP){
            x=Game.getInstance().getPlayerCharacter().getX()-1;
            y=Game.getInstance().getPlayerCharacter().getY()-18;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.LEFT){
            x=Game.getInstance().getPlayerCharacter().getX()-Game.getInstance().getPlayerCharacter().wight+12;
            y=Game.getInstance().getPlayerCharacter().getY()+(Game.getInstance().getPlayerCharacter().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.RIGHT){
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
        return Game.getInstance().getPlayerCharacter().dir == Settings.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Settings.movementDirection.DOWN;

    }}
