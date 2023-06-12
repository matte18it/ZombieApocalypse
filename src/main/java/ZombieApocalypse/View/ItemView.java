package ZombieApocalypse.View;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ItemView {
    public Image currentImage;
    Future<Image>  item;
    Future<Image> emptyImage;
    boolean isTaken=false;
    Items.ItemType type;
    public ItemView(Items.ItemType e, int wight, int height){
        type=e;
            item= ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+type+".png", wight, height, true));

        if(type!= Items.ItemType.EMPTY)
            emptyImage = ThreadPool.getExecutor().submit(()->ResourcesLoader.getInstance().getImage("/ArmieOggetti/" + Items.ItemType.EMPTY + ".png", wight, height, true));


    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void update() {
        try{
        if(isTaken)
           currentImage=emptyImage.get();
        else{
            currentImage=item.get();
       }} catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            System.exit(207);
        }

    }

    public Image getCurrentImage() {
        return  currentImage;
    }
}
