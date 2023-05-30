package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class GrenadeModel extends GunModel{
    public GrenadeModel(){
        damage=6;
        width= 21;
        height=21;
        imagePosition=new Point(0,0);
        super.setCenter();
    }
    public void attack() {
        attack=true;
        Point enemy =new Point(Game.getInstance().getEnemyCharacter().getX()+Game.getInstance().getEnemyCharacter().centerX, Game.getInstance().getEnemyCharacter().getY()+Game.getInstance().getEnemyCharacter().centerY);
        Point explosion=new Point(imagePosition.x+centerX, imagePosition.y+centerY);
        if(explosion.distance(enemy)<height)
            Game.getInstance().getEnemyCharacter().hit();

    }
    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.DOWN){
            x=Game.getInstance().getPlayerCharacter().getX()+(Game.getInstance().getPlayerCharacter().wight/2)+5;
            y=Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().height-15;
            imagePosition=new Point(x, y);


        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.UP){
            x=Game.getInstance().getPlayerCharacter().getX()-7;
            y=Game.getInstance().getPlayerCharacter().getY()-1;
            imagePosition=new Point(x, y);

        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.LEFT){
            x=Game.getInstance().getPlayerCharacter().getX()-centerX-5;
            y=Game.getInstance().getPlayerCharacter().getY()+centerY-5;
            imagePosition=new Point(x, y);


        }
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.RIGHT){
            x=Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().wight-5;
            y=Game.getInstance().getPlayerCharacter().getY()+centerY-5;
            imagePosition=new Point(x, y);

        }
    }

    public boolean isUp() {
        return Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.DOWN;

    }
}
