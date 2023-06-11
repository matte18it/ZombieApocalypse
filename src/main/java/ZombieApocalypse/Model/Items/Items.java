package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Items {
    public void dropItem(int x, int y, ItemType value) {
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
            if(!(pistol && t==3) && !(shotGun && t==1) && Game.getInstance().getWorld().isSpawnableItem(x,y) && Game.getInstance().getWorld().isWalkable(x,y)){
                c++;
                Items.getInstance().dropItem(x,y, ItemType.values()[t]);
                if(t==3 )
                   pistol=true;
                if(t==1)
                    shotGun=true;
            }


        }


    }

    public enum ItemType{ RADIO, SHOTGUN, GRENADE, PISTOL, MEDKIT, AMMO0, AMMO1, SPELL,EMPTY};
    private final ConcurrentLinkedDeque<Item> items=new ConcurrentLinkedDeque<>();
    private static final Items instance=new Items();

    public Items(){}

    public static Items getInstance(){return instance;}




    public  ConcurrentLinkedDeque<Item> getItems(){return this.items;
    }

    public void update(){
        items.removeIf(b -> !b.update());
    }
}
