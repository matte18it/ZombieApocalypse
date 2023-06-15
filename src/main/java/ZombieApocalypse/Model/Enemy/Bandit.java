package ZombieApocalypse.Model.Enemy;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import java.awt.*;
class Bandit extends Enemy{
    private int count=0;
    Bandit(int x, int y) {
        super(x,y, Enemies.EnemiesType.BANDIT);
    }
    public boolean update() {
        //Gestione della Morte
        if(dying)
            return false;
        if(healt<=0){
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.BANDIT);
            dying=true;
            Items.getInstance().enemyDrop(x,y);
            return true;
            }
        //Gestione del Ritorno al menu
        if(Game.getInstance().getBackMenu()){
            return false;
        }
        //Gestione delle Hit
        if(hit){
            if(countHit<30){
                countHit++;}
            else
                stopHit();
        }
        //Gestione del Pattern di movimento
        Point player = Game.getInstance().getPlayer().getPosition();
        Point bandit = getEnemyPosition();
        //2 pattern che si scambiamo a seconda della distanza con il player
        //Pattern 1
        if(player.distance(bandit)<300){
            if(player.y>=y && player.y<=y+height && player.x<bandit.x)
                moveRight();
            else if(player.y>=y && player.y<=y+height && player.x>=bandit.x)
                moveLeft();
            else if(player.x>=x && player.x<=x+height && player.y>=bandit.y)
                moveUp();
            else if(player.x>=x && player.x<=x+wight && player.y<bandit.y)
                moveDown();
            else{
                switch (dir){
                    case RIGHT -> moveRight();
                    case LEFT -> moveLeft();
                    case DOWN -> moveDown();
                    case UP -> moveUp();
                }
            }
            shoot();
            //Pattern 2
            }else{ isMoving=false;
        int f= random.nextInt(0,100);
        if(f<20){
        int i= random.nextInt(0,4);
        switch (i){
            case 0 ->moveUp();
            case 1 ->moveDown();
            case 2 ->moveLeft();
            case 3 ->moveRight();
        }  }else
            isMoving=false;}
        return true;}
    private void shoot() {
        //Agiornamento della posizione dello sparo
        if(count==20){
            switch (dir){
                case LEFT -> Bullets.getInstance().BulletBandit(x+wight+2,y+5, Bullet.Direction.RIGHT);
                case UP ->  Bullets.getInstance().BulletBandit(x+wight-5,y+height, Bullet.Direction.DOWN);
                case RIGHT -> Bullets.getInstance().BulletBandit(x-10,y+5, Bullet.Direction.LEFT);
                case DOWN -> Bullets.getInstance().BulletBandit(x-2,y, Bullet.Direction.UP);
            } count=0;}
        count++;
        }
    public void updateGunPosition() {
        //Aggiornamento della posizione dell'arma
        switch (dir){
            case LEFT -> {gunPosition.x=x+wight-7; gunPosition.y=y+5;}
            case DOWN -> {gunPosition.x=x-10; gunPosition.y=y-5;}
            case RIGHT -> {gunPosition.x=x-18; gunPosition.y=y+5;}
            case UP -> {gunPosition.x=x+centerX+5; gunPosition.y=y+centerY;}
        }
    }



}
