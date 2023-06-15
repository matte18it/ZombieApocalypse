package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import java.util.*;
import java.util.List;
public class Items {

    //Tipi di oggetti
    public enum ItemType{ SHOTGUN, PISTOL, GRENADE, MEDKIT, AMMO0, AMMO1, SPELL, EMPTY}
    private final List<Item> items=new Vector<>();
    private static final Items instance=new Items();
    public Items(){}
    public static Items getInstance(){return instance;}
    public  List<Item> getItems(){return this.items;
    }
    private final Random random=new Random();
    //funzione add
    public  void dropItem(int x, int y, ItemType value) {
        this.items.add(new Item(x, y, value));
    }
    //Lascia un oggetto alla morte di un nemico
    public void enemyDrop(int x, int y) {
        ItemType[] enemyPossibleDrop={ItemType.GRENADE, ItemType.MEDKIT, ItemType.AMMO0, ItemType.AMMO1, ItemType.SPELL, ItemType.EMPTY};
        int c=random.nextInt(enemyPossibleDrop.length);
        if(enemyPossibleDrop[c]!=ItemType.EMPTY) {
            Items.getInstance().dropItem(x, y, enemyPossibleDrop[c]);
        }
    }
//Funzione per spawn item
    public void generateRandomItems() {
        int count=0;
        switch (Settings.diff){
            case EASY -> count=random.nextInt(15,20);
            case MEDIUM -> count= random.nextInt(10,15);
            case HARD ->  count= random.nextInt(5,10);
        }
        int x,y;
        int c=0;
        int t;
        boolean shotGun=false; //almeno un arma ad ogni livello
        boolean pistol=false;
        while (c<count || !(pistol || shotGun)){
            t=random.nextInt(0, ItemType.values().length-1);
            if(c==count){
                int h=random.nextInt(0,100);
                if(h<50)
                    t=3;
                else
                    t=1;
            }
            x=random.nextInt(0, Settings.WINDOW_SIZEX);
            y=random.nextInt(0, Settings.WINDOW_SIZEY);
            if(!(pistol && t==ItemType.PISTOL.ordinal()) && !(shotGun && t==ItemType.SHOTGUN.ordinal()) &&  isSpawnable(x,y, ItemType.values()[t])){
                c++;
                Items.getInstance().dropItem(x,y, ItemType.values()[t]);
                if(t==ItemType.PISTOL.ordinal() )
                   pistol=true;
                if(t==ItemType.SHOTGUN.ordinal())
                    shotGun=true;
            }
        }
    }
//controlla se la posizione generata random è libera
    private boolean isSpawnable(int x, int y, ItemType value) {
        boolean distanzaPlayer=Game.getInstance().getWorld().isSpawnableItem(x+(getWight(value))/2,y+(getHeight(value)/2));
        if(distanzaPlayer){
            for( int i=x; i<getWight(value)+x; i++){
                for(int j=y; j<getHeight(value)+y; j++){
                    if(!Game.getInstance().getWorld().isWalkable(i,j))
                        return false;
                }
            } return true;

        } return false;
    }
    //altezza e larghezza di ogni item
    public int getWight(ItemType e) {
        switch (e){
            case SHOTGUN -> {return Game.getInstance().getShotgunModel().getWidth();}
            case PISTOL -> {return Game.getInstance().getPistolModel().getWidth(); }
            case GRENADE -> {return Game.getInstance().getGrenadeModel().getWidth();}
            case MEDKIT -> {return 30; }
            case AMMO1, SPELL, AMMO0 ->  {return 20;}
        } return 0;}
    public int getHeight(ItemType e) {
        switch (e){
            case SHOTGUN -> {return Game.getInstance().getShotgunModel().getHeight();}
            case PISTOL -> {return Game.getInstance().getPistolModel().getHeight(); }
            case GRENADE -> {return Game.getInstance().getGrenadeModel().getHeight();}
            case MEDKIT -> {return 30; }
            case AMMO1, SPELL, AMMO0 ->  {return 20;}
        } return 0;}
    public void update(){
        synchronized (items){
        items.removeIf(b -> !b.update());}
    }}

