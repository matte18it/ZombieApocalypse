package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

import static java.lang.Math.atan2;

public class GrenadeModel extends GunModel{
    Point mouse;
    public int [] xPosition={0,0,0};
    public int[] yPosition= {0,0,0};

    public GrenadeModel(){
        damage=6;
        width= 21;
        height=21;
        imagePosition=new Point(0,0);
        mouse=new Point(0,0);
        super.setCenter();
    }
    public void attack() {
        Point center=new Point(imagePosition.x+centerX, imagePosition.y+centerY);

        Bullets.getInstance().GrenadeLaunch(center.x, center.y, 21, 0, xPosition, yPosition);


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
        Point center=new Point(imagePosition.x+centerX, imagePosition.y+centerY);
        if (e!=null)
            mouse=e;
        int dx,dy;
        xPosition[0]=center.x;
        yPosition[0]=center.y;



        if((Math.abs(mouse.x-center.x)>=300 )){
            int c=Math.abs(mouse.x-center.x);
            c=c-300;
            if(mouse.x>center.x){
            mouse.x=mouse.x-c;}
            else
                mouse.x=mouse.x+c;

        }
        if( Math.abs(mouse.y-center.y)>=250){
            int h=Math.abs(mouse.y-center.y);
            h=h-250;
            if(mouse.y>center.y)
                mouse.y=mouse.y-h;
            else
                mouse.y=mouse.y+h;


        }
        dx=(mouse.x+center.x)/2;
        dy=((mouse.y+center.y)/2)-60;
        xPosition[1]=dx;
        xPosition[2]=mouse.x;
        yPosition[1]=dy;
        yPosition[2]=mouse.y;









        }


    public boolean isUp() {
        return Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Character.movementDirection.DOWN;

    }
}
