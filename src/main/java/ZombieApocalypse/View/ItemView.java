package ZombieApocalypse.View;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ItemView {
    public Image currentImage;
    ThreadPool  item;
    public ItemView(Items.ItemType type, int wight, int height){
            item= new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+type+".png", wight, height, true));

    }


    public void update() {
        currentImage=item.get();
    }

    public Image getCurrentImage() {
        return  currentImage;
    }
}
