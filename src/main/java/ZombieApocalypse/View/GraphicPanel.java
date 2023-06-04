package ZombieApocalypse.View;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Item;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Gun.GrenadeView;
import ZombieApocalypse.View.Gun.PistolView;
import ZombieApocalypse.View.Gun.KnifeView;
import ZombieApocalypse.View.Gun.ShotgunView;
import ZombieApocalypse.View.Player.CharacterView;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {
    int count;
    //Disegna il mondo
    private final CharacterView characterView = new CharacterView();
    private static final PistolView pistolView = new PistolView();
    private static final ShotgunView shotgunView=new ShotgunView();
    private static final GrenadeView grenadeView=new GrenadeView();
    private static final KnifeView knife=new KnifeView();
    public GrenadeView getGrenadeView(){return grenadeView;}

    public PistolView getPistolView(){
        return pistolView;
    }
    public ShotgunView getShotgunView() {
        return shotgunView;
    }
    //immagini ordinate fino ad adesso
    private final int numeroImmagini=1;
    private final Image[] images=new Image[numeroImmagini];
    public GraphicPanel()  {
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
        for(int i=0; i<numeroImmagini; i++){
            String c=String.valueOf(i);
            String path="/AmbienteDiGioco/Terreno/Terreno"+c+".png";
            images[i]= ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        }

    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        World world=new World();
        for(int i = 0; i < world.getSize(); i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.getSize(); j++) {
                int y = j * Settings.CELL_SIZEY;
                if(world.isGround0(i, j)) {
                    //Random random=new Random();
                    //int value= random.nextInt(numeroImmagini);
                    g.drawImage(images[0], x, y, null);
                }

            }


        }
        if(Game.getInstance().hasPistol){
        if(Game.getInstance().getPistolModel().isUp()){
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistolModel().imagePosition.x, Game.getInstance().getPistolModel().imagePosition.y, Game.getInstance().getPistolModel().getHeight(), Game.getInstance().getPistolModel().getWidth(), null);
        }else{
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistolModel().imagePosition.x, Game.getInstance().getPistolModel().imagePosition.y, Game.getInstance().getPistolModel().getWidth(), Game.getInstance().getPistolModel().getHeight(), null);
        }}
        if(Game.getInstance().hasShotgun){
            if(Game.getInstance().getShotgunModel().isUp()){
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgunModel().imagePosition.x, Game.getInstance().getShotgunModel().imagePosition.y, Game.getInstance().getShotgunModel().getHeight(), Game.getInstance().getShotgunModel().getWidth(), null);
            }else{
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgunModel().imagePosition.x, Game.getInstance().getShotgunModel().imagePosition.y, Game.getInstance().getShotgunModel().getWidth(), Game.getInstance().getShotgunModel().getHeight(), null);
            }}
        if(count==0){
            //per adesso gestione nemici
            Enemies.getInstance().addSkinnyZombie(500,500);
            //per adesso gestione item
            Items.getInstance().dropItem(200,350, Items.ItemType.MEDKIT);
            Items.getInstance().dropItem(100,150, Items.ItemType.SPELL);
            Items.getInstance().dropItem(80,150, Items.ItemType.SPELL);
            Items.getInstance().dropItem(150, 100, Items.ItemType.RADIO);
            Items.getInstance().dropItem(50, 100, Items.ItemType.SHOTGUN);
            Items.getInstance().dropItem(250, 100, Items.ItemType.PISTOL);
            Items.getInstance().dropItem(300, 100, Items.ItemType.GRENADE);
            Items.getInstance().dropItem(350, 100, Items.ItemType.GRENADE);
            Items.getInstance().dropItem(80, 120, Items.ItemType.AMMO0);
            Items.getInstance().dropItem(120, 120, Items.ItemType.AMMO1);


        }
        for (Enemy b : Enemies.getInstance().getEnemies()) {
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);
        }
        for (Item b : Items.getInstance().getItems()) {
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);
        }
        if(Game.getInstance().getPlayerCharacter().speedUp ){
        g.setColor(Color.WHITE);
        float t= (float) Game.getInstance().getPlayerCharacter().countSpeed /60;
            String h = String.format("%.2f", t);
        g.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        String c="SpeedUp: "+String.valueOf(h);
        g.drawString(c, 20, 20);}


        count++;

        for (Bullet b : Bullets.getInstance().getBullets()) {
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getDimension(), b.getDimension(), null);
        }




        if(Game.getInstance().hasKnife){
            if(Game.getInstance().getKnifeModel().isUp())
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnifeModel().imagePosition.x,Game.getInstance().getKnifeModel().imagePosition.y, Game.getInstance().getKnifeModel().getHeight(), Game.getInstance().getKnifeModel().getWidth(), null);
            else
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnifeModel().imagePosition.x,Game.getInstance().getKnifeModel().imagePosition.y, Game.getInstance().getKnifeModel().getWidth(), Game.getInstance().getKnifeModel().getHeight(), null);

        }
        if(Game.getInstance().hasGrenade){

            if(Game.getInstance().getGrenadeModel().isUp())
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenadeModel().imagePosition.x, Game.getInstance().getGrenadeModel().imagePosition.y, Game.getInstance().getGrenadeModel().getHeight(), Game.getInstance().getGrenadeModel().getWidth(), null);
            else
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenadeModel().imagePosition.x, Game.getInstance().getGrenadeModel().imagePosition.y, Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), null);

        }
        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayerCharacter().getX(), Game.getInstance().getPlayerCharacter().getY(), characterView.width, characterView.height, null);



    }
    public void update() {
        characterView.update();
        //Sposto l'arma dove è il character senza girarla
        if(Game.getInstance().hasPistol)
            pistolView.update(null);
        if(Game.getInstance().hasKnife)
            knife.update();
        if(Game.getInstance().hasShotgun)
            shotgunView.update(null);
        if(Game.getInstance().hasGrenade){
            grenadeView.update();
            grenadeView.update(null);}
        Bullets.getInstance().update();
        Items.getInstance().update();
        Enemies.getInstance().update();
        repaint();
    }


}

