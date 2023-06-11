package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class GrenadeModel extends GunModel{
    Point mouse;


    public GrenadeModel(){
        damage=10;
        switch (Settings.diff){
            case EASY -> damage=damage*2;
            case HARD -> damage=damage/2;
        }
        width= 21;
        height=21;
        mouse=new Point(0,0);

        imagePosition=new Point(0,0);

        super.setCenter();
    }
    public void attack() {
        Point center=new Point(imagePosition.x+centerX, imagePosition.y+centerY);
        Bullet.Direction dir = checkDirection(angle);

        Bullets.getInstance().GrenadeLaunch(center.x, center.y, 21,  0, dir, (int)center.distance(mouse));


    }
    public void update(){
        int x;
        int y;
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.DOWN){
            x=Game.getInstance().getPlayerCharacter().getX()+(Game.getInstance().getPlayerCharacter().wight/2)+5;
            y=Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().height-15;
            imagePosition=new Point(x, y);


        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.UP){
            x=Game.getInstance().getPlayerCharacter().getX()-7;
            y=Game.getInstance().getPlayerCharacter().getY()-1;
            imagePosition=new Point(x, y);

        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.LEFT){
            x=Game.getInstance().getPlayerCharacter().getX()-centerX-5;
            y=Game.getInstance().getPlayerCharacter().getY()+centerY-5;
            imagePosition=new Point(x, y);


        }
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.RIGHT){
            x=Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().wight-5;
            y=Game.getInstance().getPlayerCharacter().getY()+centerY-5;
            imagePosition=new Point(x, y);

        }
    }
    public void update(Point e){
        Point center=new Point(imagePosition.x+centerX, imagePosition.y+centerY);

        if (e!=null)
            mouse=e;
        while (center.distance(mouse)>300) {
            if (center.x > mouse.x)
                mouse.x++;
            else
                mouse.x--;
            if(center.y>mouse.y)
                mouse.y++;
            else
                mouse.y--;
        }
        float xDistance = mouse.x - Game.getInstance().getPlayerCharacter().getX();   //Distanza punto x
        float yDistance = mouse.y - Game.getInstance().getPlayerCharacter().getY();     //Distanza punto y
        //Questo metodo converte le coordinate rettangolari (x,y) in coordinate polari (r,theta) e ritorna theta
        angle = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        //Le coordinate sotto lo zero diventano negative, a noi ci servono sempre positive
        if(angle<0)
            angle=angle+360;
    }


    public boolean isUp() {
        return Game.getInstance().getPlayerCharacter().dir == Settings.movementDirection.UP || Game.getInstance().getPlayerCharacter().dir == Settings.movementDirection.DOWN;

    }
}
