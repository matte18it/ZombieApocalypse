package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  class Item {
    int x;
    int y;
    int wight, height;
    ItemView view;
    Items.ItemType type;
    Rectangle hitBox;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getWight() {
        return wight;
    }
    public int getHeight(){return height;}
    public ItemView getView(){
        return view;
    }
    Item(int x, int y, Items.ItemType e){
        this.wight=Items.getInstance().getWight(e);
        this.height=Items.getInstance().getHeight(e);
        this.x=x;
        this.y=y;
        this.view=new ItemView(e, wight, height);
        this.type=e;
        this.hitBox=new Rectangle(x,y,wight,height);
    }
    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerHitBox()) ){
             if(Game.getInstance().iCanCollect() && (type!= Items.ItemType.AMMO0 && type!= Items.ItemType.AMMO1)){
                    Game.getInstance().addItemInMenuBar(type);
                    return false;
        }
         if (Game.getInstance().iCanCollectAmmo(type) && (type== Items.ItemType.AMMO0 || type== Items.ItemType.AMMO1)) {
                Game.getInstance().collectAmmo(type);
                return false;
            }
        }
        return !Game.getInstance().getBackMenu();
    }}
