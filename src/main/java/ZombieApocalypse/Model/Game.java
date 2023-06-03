package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Guns.GrenadeModel;
import ZombieApocalypse.Model.Guns.KnifeModel;
import ZombieApocalypse.Model.Guns.PistolModel;
import ZombieApocalypse.Model.Guns.ShotgunModel;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.Gun.GrenadeView;
import ZombieApocalypse.View.MenuBar.MenuBarView;

import java.awt.*;

public class Game {
    //Gestisce gli aspetti del gioco
    private final World world = new World();
    private final PlayerCharacter character = new PlayerCharacter();
    private final KnifeModel knife=new KnifeModel();
    private final PistolModel pistol = new PistolModel();
    private final GrenadeModel grenade=new GrenadeModel();
    private final ShotgunModel shotgun=new ShotgunModel();
    private boolean pause = false;

    //Gestione delle armi, per adesso
    public boolean hasGrenade=false;
    public boolean hasPistol=false;
    public boolean hasKnife=false;
    public boolean hasShotgun=true;

    private EnemyCharacter enemy = new EnemyCharacter();
    private MenuBarModel menuBar;
    public void setMenuBar(MenuBarView m){
        menuBar=new MenuBarModel(m);
    }
    public MenuBarModel getMenuBar(){
        return menuBar;
    }
    public KnifeModel getKnifeModel(){
        return knife;
    }
    public ShotgunModel getShotgunModel(){
        return shotgun;
    }


    private static Game instance = new Game();
    private Game() {}
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
    public PistolModel getPistolModel() {
        return pistol;}


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
        if(hasPistol )
            pistol.attack();
        if(hasKnife)
            knife.attack();
        if(hasShotgun)
            shotgun.attack();
        if(hasGrenade)
            grenade.attack();
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

    public void refresh(){
        instance = new Game();
    }
    private boolean backMenu=false;

    public void setBackMenu(boolean e){
        backMenu=e;
    }

    public void updateTime(long time) {
            menuBar.updateTimeLable(time);
    }

    public GrenadeModel getGrenadeModel() {
        return grenade;
    }

    public boolean getPause(){
        return  pause;
    }
    public void setPause(boolean pause){
        this.pause = pause;
    }

    public boolean getBackMenu() {
        return backMenu;
    }

    public void useLeftItem() {
        menuBar.useItem(true);
    }

    public void useRightItem() {
        menuBar.useItem(false);
    }

    public void dropItem() {
        boolean b=true;
        Items.ItemType value=menuBar.getLabel1();
        if(value== Items.ItemType.EMPTY){
            value=menuBar.getLabel2();
        b=false;}

        if(value!= Items.ItemType.EMPTY){
            if(world.isGround0(character.getX()+character.wight+10, character.getY())){
                Items.getInstance().dropItem(character.getX()+character.wight+10, character.getY(), value);
                if(b)
                    menuBar.setLabel1Empty();
                else
                    menuBar.setLabel2Empty();
            }

    }}
}
