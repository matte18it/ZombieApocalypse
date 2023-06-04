package ZombieApocalypse.Model.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Items {
    public void dropItem(int x, int y, ItemType value) {
        this.items.add(new Item(x, y, value));
    }

    public enum ItemType{AMMO0, AMMO1, SPELL, RADIO, SHOTGUN, MEDKIT, GRENADE, PISTOL, EMPTY};
    private final List<Item> items=new ArrayList();
    private static final Items instance=new Items();

    public Items(){}

    public static Items getInstance(){return instance;}




    public List<Item> getItems(){return Collections.unmodifiableList(this.items);
    }

    public void update(){
        Iterator var1=this.items.iterator();
        while(var1.hasNext()){
            Item b=(Item) var1.next();
            if(!b.update()){
                items.remove(var1);}

        }
    }
}
