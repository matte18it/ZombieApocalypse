package ZombieApocalypse.Model.Guns;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import java.awt.*;
public class GrenadeModel extends GunModel{
    double angle=0;
    Point mouse=new Point(0,0);
    public GrenadeModel(){
        super(21,21, 10);
    }
    public void attack() {
        //Lancio la granata, calcolo a quale distanza devo lanciarla
        Point center=new Point(imagePosition.x+centerX, imagePosition.y+centerY);
        Bullet.Direction dir = checkDirection(angle);
        Bullets.getInstance().GrenadeLaunch(center.x, center.y,dir, (int)center.distance(mouse));
    }
    public void update(){
        int x;
        int y;
        //Posizionamento della granata secondo la posizione del player e la dir
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.DOWN){
            x= Game.getInstance().getPlayer().getX()+25;
            y=Game.getInstance().getPlayer().getY()+18;
            imagePosition=new Point(x, y);
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP){
            x=Game.getInstance().getPlayer().getX()-7;
            y=Game.getInstance().getPlayer().getY()-1;
            imagePosition=new Point(x, y);
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.LEFT){
            x=Game.getInstance().getPlayer().getX()-15;
            y=Game.getInstance().getPlayer().getY()+5;
            imagePosition=new Point(x, y);
        }
        if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.RIGHT){
            x=Game.getInstance().getPlayer().getX()+35;
            y=Game.getInstance().getPlayer().getY()+5;
            imagePosition=new Point(x, y);
        }
    }


    public void update(Point e){
        //Aggiorna l'angolo di lancio per il mouse, che verra usato per il lancio della granata
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
        float xDistance = mouse.x - Game.getInstance().getPlayer().getX();   //Distanza punto x
        float yDistance = mouse.y - Game.getInstance().getPlayer().getY();    //Distanza punto y
        //Questo metodo converte le coordinate rettangolari (x,y) in coordinate polari (r,theta) e ritorna theta
        angle = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        //Le coordinate sotto lo zero diventano negative, a noi ci servono sempre positive
        if(angle<0)
            angle=angle+360;
    }

}
