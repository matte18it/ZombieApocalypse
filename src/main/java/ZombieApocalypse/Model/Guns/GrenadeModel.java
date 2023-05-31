package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class GrenadeModel extends GunModel{
    public int xAngle;
    public int yAngle;
    public int wightAngle;
    public int heightAngle;
    public int startAngle;
    public int endAngle;
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
    public void update(Point e){
        if(e==null){
            return;
        }else {
            //dobbiamo calcolare il centro del nostro arco
            //utilizzando la formula per trovare un punto equidistante da altri due
            Point centerImage = new Point(imagePosition.x + centerX, imagePosition.y + centerY);

            //formula: ((x1+x2)/2, (y1+y2)/2)
            Point circleCenter = new Point((centerImage.x + e.x) / 2, (centerImage.y + e.y) / 2);
            int x=centerImage.x-circleCenter.x;
            int y=centerImage.y-circleCenter.y;
            int raggio = (int) Math.sqrt((x*x)+ (y*y));

            float q=e.y-centerImage.y;
            float t=e.x-centerImage.x;

            xAngle = Math.min(e.x, centerImage.x);
            yAngle=Math.min(e.y, centerImage.y);



            wightAngle = Math.abs(e.x-centerImage.x);
            heightAngle = Math.abs(e.y-centerImage.y);
            startAngle =  (int)(Math.atan2(e.y-centerImage.y, e.x-centerImage.y)*180 /Math.PI);
            endAngle = 180;


        }
    }

    public boolean isUp() {
        return Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.DOWN;

    }
}
