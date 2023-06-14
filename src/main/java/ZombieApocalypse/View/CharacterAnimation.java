package ZombieApocalypse.View;

import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.ArrayList;

public class CharacterAnimation  {
    //Carica le animazioni richieste

    private final ArrayList<ThreadPool> images = new ArrayList<>();
    private int index = 0;




    public CharacterAnimation(String name, int numberOfElement) {
        ThreadPool temp=null;
        for (int i = 0; i < numberOfElement; i++) {
            String path=name+i;
            temp=new ThreadPool(ResourcesLoader.getInstance().getImage("/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, false));
            images.add(temp);
        }
    }


    public Image getDefaultImage() {
        return images.get(0).get();
    }

    public Image update() {

        index = (index+1) % images.size();
        return images.get(index).get();
    }
    public Image getCurrentImage(){
        return images.get(index).get();
    }




}
