package ZombieApocalypse.Model;
import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Guns.*;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResultsPanel;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.MenuBar.MenuBarView;
import java.awt.*;
public class Game {
    //Gestisce gli aspetti del gioco
    private static Game instance = new Game();
    private Game() {}
    public static Game getInstance() {return instance;}
    //reset del game
    public void refresh(){
        instance = new Game();
    }
    private final PlayerCharacter character = new PlayerCharacter();
    private  World world=new World();
    private final KnifeModel knife=new KnifeModel();
    private final PistolModel pistol = new PistolModel();
    private final GrenadeModel grenade=new GrenadeModel();
    private final ShotgunModel shotgun=new ShotgunModel();
    private boolean pause = false;
    private boolean backMenu=false;
    public boolean hasGrenade=false;
    public boolean hasPistol=false;
    public boolean hasKnife=true;
    public boolean hasShotgun=false;
    private MenuBarModel menuBar;
    private Point mousePoint=new Point(0,0);
    //Gestione del Player
    public PlayerCharacter getPlayerCharacter() {return character;}
    public void startMovementRight() {character.startMovementRight();}
    public void startMovementUp() {character.startMovementUp();}
    public void startMovementDown() {
        character.startMovementDown();
    }
    public void startMovementLeft() {
        character.startMovementLeft();
    }
    public void stopMovement() {
        character.stopMovement();
    }
    public int getPlayerLife() {return character.health;}
    public int getPlayerMaxLife() {return character.getMaxHealth();}
    public Settings.movementDirection getPlayerDirection() {return character.dir;}
    public void speedUpPlayer() {
        character.speedUp();
    }
    public Point getPlayerPosition() {return new Point(character.getX()+character.centerX, character.getY()+character.centerY);}
//Gestione delle Armi e input del mouse
    public KnifeModel getKnifeModel(){
    return knife;
}
    public ShotgunModel getShotgunModel(){
        return shotgun;
    }
    public GrenadeModel getGrenadeModel() {
    return grenade;
}
    public PistolModel getPistolModel() {return pistol;}
    public void setLastMousePosition(Point e) {mousePoint=e;}//Si salva l'ultima posizione del mouse per riprenderla al cambio arma
public void attack() {  //gestione del click del mouse
    if(hasPistol && menuBar.numBullet()>0 ){
        pistol.attack();
        menuBar.removeAmmo(1);}
    if(hasKnife)
        knife.attack();
    if(hasShotgun && menuBar.numBullet()>2 ){
        shotgun.attack();
        menuBar.removeAmmo(3);}
    if(hasGrenade){
        grenade.attack();
        menuBar.setLabelEmpty(itemUsed);
        setKnife();}
}
    private void setKnife() {
        hasGrenade=false;
        hasPistol=false;
        hasKnife=true;
        hasShotgun=false;
    }
    public void setShotgun() {
        if(hasShotgun)
            setKnife();
        else {
            hasGrenade=false;
            hasPistol=false;
            hasKnife=false;
            hasShotgun=true;
            shotgun.update(mousePoint);}
    }
    public void setPistol() {
        if(hasPistol)
            setKnife();
        else{
            hasGrenade=false;
            hasPistol=true;
            hasKnife=false;
            hasShotgun=false;
            pistol.update(mousePoint);}
    }
    public void setGrenade() {
        if(hasGrenade)
            setKnife();
        else{
            hasGrenade=true;
            hasPistol=false;
            hasKnife=false;
            hasShotgun=false;
            grenade.update(mousePoint);}
    }
    //Gestione del Mondo
    public World getWorld(){
        return world;
    }
    public void reloadWorld(){ //Reload ad ogni cambio mappa
        world=new World();
        character.chooseCoordinate();
    }
//Gestione di Pausa e ritorno al menu
    public boolean getPause(){
    return !pause;
}
    public void setPause(boolean pause){
        this.pause = pause;
    }
    public void setBackMenu(boolean e){
        backMenu=e;
    }
    public boolean getBackMenu() {
        return backMenu;
    }
    //Update del timeLoop
    public void updateTime(long time) {
            menuBar.updateTimeLable(time);
    }
    //Gestione della MenuBar
    public void setMenuBar(MenuBarView m){
        menuBar=new MenuBarModel(m);
    }
    public MenuBarModel getMenuBar(){
        return menuBar;
    }
    //Utilizzo degli Item della menu bar, true : slot di Sinistra sennò di destra
    boolean itemUsed;
    public void useLeftItem() {
        itemUsed=true;
        menuBar.useItem(itemUsed);
    }
    public void useRightItem() {
        itemUsed=false;
        menuBar.useItem(itemUsed);
    }
    //Drop degli item tramite Barra Spaziatrice
    public void dropItem() {
        boolean b=true;
        Items.ItemType value=menuBar.getLabel1();
        if(value== Items.ItemType.EMPTY){
            value=menuBar.getLabel2();
        b=false;}

        if(value!= Items.ItemType.EMPTY){
            if(world.isWalkable(character.getX()+character.wight+10, character.getY())){
                Items.getInstance().dropItem(character.getX()+character.wight+10, character.getY(), value);
                menuBar.setLabelEmpty(b);
                if(value== Items.ItemType.SHOTGUN || value== Items.ItemType.PISTOL || value==Items.ItemType.GRENADE)
                    setKnife();
            }
    }}
    //Update del Game, e controllo schermata di vittoria
    public void update()  {
        if(character.isMoving())
            character.move();
        if(character.hit)
            character.addHit();
        if(character.speedUp)
            character.addSpeed();
        if(Enemies.getInstance().enemyNumber == 0 && !Game.getInstance().getBackMenu()){
            Game.getInstance().setPause(true);
            if(!Settings.isEditor){
                if(Settings.nextCampaignMap())
                    ResultsPanel.getInstance().showContinue();
                else
                    ResultsPanel.getInstance().showFinal();
            }
            else
                ResultsPanel.getInstance().showFinal();}
        if(Game.getInstance().getPlayerLife() == 0){
            Game.getInstance().setPause(false);
            ResultsPanel.getInstance().showGameOver();
        }}











}
