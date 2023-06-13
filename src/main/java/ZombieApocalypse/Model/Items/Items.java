package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Items {
    public synchronized void dropItem(int x, int y, ItemType value) {
        this.items.add(new Item(x, y, value));
    }
Random m=new Random();
    public void generateRandomItems() {
        int count=0;
        switch (Settings.diff){
            case EASY -> count=m.nextInt(10,18);
            case MEDIUM -> count= m.nextInt(5,10);
            case HARD ->  count= m.nextInt(3,5);
        }

        int x,y;
        int c=0;
        int t;
        boolean shotGun=false;
        boolean pistol=false;
        while (c<count || !(pistol || shotGun)){
            t=m.nextInt(1, ItemType.values().length);
            if(c==count){
                int h=m.nextInt(0,100);
                if(h<50)
                    t=3;
                else
                    t=1;

            }
            x=m.nextInt(0, Settings.WINDOW_SIZEX);
            y=m.nextInt(0, Settings.WINDOW_SIZEY);
            if(!(pistol && t==3) && !(shotGun && t==1) &&  isSpawnable(x,y, ItemType.values()[t])){
                c++;
                Items.getInstance().dropItem(x,y, ItemType.values()[t]);
                if(t==3 )
                   pistol=true;
                if(t==1)
                    shotGun=true;
            }


        }


    }

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

    public int getWight(ItemType e) {
        switch (e){
            case SHOTGUN -> {return Game.getInstance().getShotgunModel().getWidth();}
            case PISTOL -> {return Game.getInstance().getPistolModel().getWidth(); }
            case GRENADE -> {return Game.getInstance().getGrenadeModel().getWidth();}
            case MEDKIT, RADIO, EMPTY -> {return 30; }
            case AMMO1, SPELL, AMMO0 ->  {return 20;}

        } return 0;}



    public int getHeight(ItemType e) {
        switch (e){
            case SHOTGUN -> {return Game.getInstance().getShotgunModel().getHeight();}
            case PISTOL -> {return Game.getInstance().getPistolModel().getHeight(); }
            case GRENADE -> {return Game.getInstance().getGrenadeModel().getHeight();}
            case MEDKIT, RADIO, EMPTY -> {return 30; }
            case AMMO1, SPELL, AMMO0 ->  {return 20;}

        } return 0;}

    public enum ItemType{ RADIO, SHOTGUN, GRENADE, PISTOL, MEDKIT, AMMO0, AMMO1, SPELL,EMPTY};
    private final ArrayList<Item> items=new ArrayList<>();
    private static final Items instance=new Items();

    public Items(){}

    public static Items getInstance(){return instance;}




    public  ArrayList<Item> getItems(){return this.items;
    }

    public void update(){
        items.removeIf(b -> !b.update());
    }}

