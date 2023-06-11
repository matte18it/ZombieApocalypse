package ZombieApocalypse.View;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;

public class ItemView {
    public Image currentImage;
    Image[] items =new Image[Items.ItemType.values().length];
    boolean isTaken=false;
    Items.ItemType type;
    public ItemView(Items.ItemType e, int wight, int height){
        type=e;
        if(items[type.ordinal()]==null){
            items[type.ordinal()]= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+type+".png", wight, height, true);
            }
        if(items[type.ordinal()]==null)
            items[Items.ItemType.EMPTY.ordinal()] = ResourcesLoader.getInstance().getImage("/ArmieOggetti/" + Items.ItemType.EMPTY + ".png", wight, height, true);


    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void update() {
        if(isTaken)
           currentImage=items[Items.ItemType.EMPTY.ordinal()];
        else{
            currentImage=items[type.ordinal()];
       }

    }

    public Image getCurrentImage() {
        return  currentImage;
    }
}
