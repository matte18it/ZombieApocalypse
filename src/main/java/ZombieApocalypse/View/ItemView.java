package ZombieApocalypse.View;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;

public class ItemView {
    public Image currentImage;
    Image items[]=new Image[7];
    boolean isTaken=false;
    Items.ItemType type;
    public ItemView(Items.ItemType e, int dimesion){
        type=e;
        if(items[type.ordinal()]==null){
            items[type.ordinal()]= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+type+".png", dimesion, dimesion, true);
            }
        if(items[Items.ItemType.EMPTY.ordinal()]==null){
            items[Items.ItemType.EMPTY.ordinal()]= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+Items.ItemType.EMPTY +".png", dimesion, dimesion, true);
        }

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
