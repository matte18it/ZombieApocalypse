package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

import static ZombieApocalypse.Model.Game.*;

public class KnifeModel extends GunModel{

    public KnifeModel(){
        width= Settings.CELL_SIZEX-5;
        height=Settings.CELL_SIZEY/2;
        imagePosition=new Point(0,0);
        hitBox=new Rectangle(imagePosition.x, imagePosition.y, width,height);
        damage=4;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }


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
        if(getInstance().getPlayerCharacter().dir== Settings.movementDirection.DOWN){
            x= getInstance().getPlayerCharacter().getX()+(getInstance().getPlayerCharacter().wight/2)+5;
            y= getInstance().getPlayerCharacter().getY()+ getInstance().getPlayerCharacter().height-15;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(getInstance().getPlayerCharacter().dir== Settings.movementDirection.UP){
            x= getInstance().getPlayerCharacter().getX()-1;
            y= getInstance().getPlayerCharacter().getY()-18;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=width;
            hitBox.width=height;
        }
        if(getInstance().getPlayerCharacter().dir== Settings.movementDirection.LEFT){
            x= getInstance().getPlayerCharacter().getX()- getInstance().getPlayerCharacter().wight+12;
            y= getInstance().getPlayerCharacter().getY()+(getInstance().getPlayerCharacter().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }
        if(getInstance().getPlayerCharacter().dir== Settings.movementDirection.RIGHT){
            x= getInstance().getPlayerCharacter().getX()+ getInstance().getPlayerCharacter().wight-5;
            y= getInstance().getPlayerCharacter().getY()+(getInstance().getPlayerCharacter().height/2)-3;
            imagePosition=new Point(x, y);
            hitBox.x=x;
            hitBox.y=y;
            hitBox.height=height;
            hitBox.width=width;
        }

    }

    public boolean isUp(){
        return getInstance().getPlayerCharacter().dir == Settings.movementDirection.UP || getInstance().getPlayerCharacter().dir == Settings.movementDirection.DOWN;

    }}
