package ZombieApocalypse.Model.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Items {
    public void dropItem(int x, int y, ItemType value) {
        this.items.add(new Item(x, y, value));
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
