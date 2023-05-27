package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.MenuBar.MenuBarView;

import java.awt.*;

public class Game {
    //Gestisce gli aspetti del gioco
    private final World world = new World();
    private final PlayerCharacter character = new PlayerCharacter();
    private final KnifeModel knife=new KnifeModel();
    public boolean hasGun=false;
    public boolean hasKnife=true;
    private final EnemyCharacter enemy = new EnemyCharacter();
    private  MenuBarModel menuBar;
    public void setMenuBar(MenuBarView m){
        menuBar=new MenuBarModel(m);
    }
    public MenuBarModel getMenuBar(){
        return menuBar;
    }
    public KnifeModel getKnifeModel(){
        return knife;
    }
    private final GunModel gun = new GunModel();

    private static final Game instance = new Game();
    private Game() {
    }
    public static Game getInstance() {
        return instance;
    }

    public void startMovementRight() {
        character.startMovementRight();}
    public void startMovementUp() {
        character.startMovementUp();

    }

    public void startMovementDown() {
        character.startMovementDown();
    }
    public void startMovementLeft() {
        character.startMovementLeft();
    }

    public void stopMovement() {
        character.stopMovement();
    }


    //Da fare nel Game Loop
    public void update() {
        if(character.isMoving())
            character.move();}



    public PlayerCharacter getPlayerCharacter() {
        return character;}
    public GunModel getGunModel() {
        return gun;}


    public int getPlayerLife() {
        return character.health;}
    public int getPlayerMaxLife() {
        return character.getMaxHealth();}

    public PlayerCharacter.movementDirection getPlayerDirection() {
        return character.dir;}

    public void closeGame() {
        GameFrame.close();
    }







    public void attack() {
        if(hasGun)
            gun.attack();
        if(hasKnife)
            knife.attack();

    }

    public Point coordinateToIJ(int x, int y) {
        int x1=x* Settings.WORLD_SIZEX/Settings.WINDOW_SIZEX;
        int y1=y* Settings.WORLD_SIZEY/Settings.WINDOW_SIZEY;
        return new Point(x1,y1);
    }

    public EnemyCharacter getEnemyCharacter() {
        return enemy;
    }

    public void checkCollision() {
        //idea: creare un'array di nemici da scorrere
        //ora ne abbiamo solo uno
        //30 frame per hit

        if(Game.getInstance().getEnemyCharacter().countHit==30)
            Game.getInstance().getEnemyCharacter().stopHit();
        if(character.countHit==30)
            character.stopHit();
        if(character.hitBox.intersects(enemy.hitBox) ){
            character.hit();
        }


    }

    public void updateTime(long time) {
        menuBar.updateTimeLable(time);
    }
}
