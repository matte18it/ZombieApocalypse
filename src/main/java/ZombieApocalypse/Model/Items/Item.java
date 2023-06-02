package ZombieApocalypse.Model.Items;

import ZombieApocalypse.View.ItemView;

import java.awt.*;

public abstract class Item {
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
    public abstract boolean update();
    public Item(int x, int y, int dim, Items.ItemType e){
        this.x=x;
        this.y=y;
        this.dimension=dim;
        this.view=new ItemView(e, dimension);
        this.type=e;
        this.hitBox=new Rectangle(x,y,dim,dim);
    }
}
