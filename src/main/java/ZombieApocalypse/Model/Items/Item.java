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
            default ->{this.wight=30; this.height=30;}
        }
        this.view=new ItemView(e, wight, height);
        this.type=e;
        this.hitBox=new Rectangle(x,y,wight,height);
        taken=true;
    }

    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox) && taken && Game.getInstance().getMenuBar().collect()){
            getView().setTaken(true);
            taken=false;
            Game.getInstance().getMenuBar().addItem(type);
        }
        if(Game.getInstance().getBackMenu()){
            getView().setTaken(true);
            taken=false;
        }

        return taken;
}}
