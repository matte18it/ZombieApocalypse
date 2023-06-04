package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  class Item {
    int x;
    int y;
    int wight, height;

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
   ItemView view;
    Items.ItemType type;
    Rectangle hitBox;
    public ItemView getView(){
        return view;
    }
public boolean taken=true;
    public Item(int x, int y, Items.ItemType e){

        this.x=x;
        this.y=y;
        switch (e){
            case SPELL -> {this.wight=20; this.height=20;}
            case SHOTGUN -> {this.wight=Game.getInstance().getShotgunModel().getWidth(); this.height=Game.getInstance().getShotgunModel().getHeight();}
            case PISTOL -> {this.wight=Game.getInstance().getPistolModel().getWidth(); this.height=Game.getInstance().getPistolModel().getHeight();}
            case GRENADE -> {this.wight=Game.getInstance().getGrenadeModel().getWidth(); this.height=Game.getInstance().getGrenadeModel().getHeight();}
            case MEDKIT -> {this.wight=30; this.height=30;}
            case RADIO -> {this.wight=30; this.height=30;}
            case AMMO0 -> {this.wight=20; this.height=20;}
            case AMMO1 -> {this.wight=20; this.height=20;}
        }
        this.view=new ItemView(e, wight, height);
        this.type=e;
        this.hitBox=new Rectangle(x,y,wight,height);
        taken=true;
    }

    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox) && taken){
             if(Game.getInstance().getMenuBar().collect() && (type!= Items.ItemType.AMMO0 && type!= Items.ItemType.AMMO1)){

                    Game.getInstance().getMenuBar().addItem(type);
                 getView().setTaken(true);
                 taken=false;

        }
         if (Game.getInstance().getMenuBar().collectAmmo(type) && (type== Items.ItemType.AMMO0 || type== Items.ItemType.AMMO1)) {
                Game.getInstance().getMenuBar().addAmmo(type);
             getView().setTaken(true);
             taken=false;
            }

        }

        if(Game.getInstance().getBackMenu()){
            getView().setTaken(true);
            taken=false;
        }

        return taken;
}}
