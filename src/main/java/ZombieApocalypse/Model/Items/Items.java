package ZombieApocalypse.Model.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Items {
    public void dropItem(int x, int y, ItemType value) {
        this.items.add(new Item(x, y, value));
    }

    public enum ItemType{ RADIO, SHOTGUN, GRENADE, PISTOL, MEDKIT, AMMO0, AMMO1, SPELL,EMPTY};
    private final List<Item> items=new ArrayList<>();
    private static final Items instance=new Items();

    public Items(){}

    public static Items getInstance(){return instance;}




    public final List<Item> getItems(){return this.items;
    }

    public void update(){
        Iterator<Item> var1=items.iterator();
        while(var1.hasNext()){
            Item b=  var1.next();
            if(!b.update()){
                var1.remove();}

        }
    }
}
