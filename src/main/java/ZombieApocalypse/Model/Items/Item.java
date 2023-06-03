package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.ItemView;

import java.awt.*;

public  class Item {
    int x;
    int y;
    int dimension;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDimension() {
        return dimension;
    }
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
            case MEDKIT -> this.dimension=30;
            case SPELL -> this.dimension=20;
        }
        this.view=new ItemView(e, dimension);
        this.type=e;
        this.hitBox=new Rectangle(x,y,dimension,dimension);
        taken=true;
    }

    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox) && taken && Game.getInstance().getMenuBar().collect()){
            getView().setTaken(true);
            taken=false;
            Game.getInstance().getMenuBar().addItem(type);
        }
        return taken;
}}
