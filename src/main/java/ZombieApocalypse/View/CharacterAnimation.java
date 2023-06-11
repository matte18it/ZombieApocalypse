package ZombieApocalypse.View;

import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.ArrayList;

public class CharacterAnimation  {
    //Carica le animazioni richieste

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    public ArrayList<Image> getImages(){
        return images;
    }



    public CharacterAnimation(String name, int numberOfElement) {
        for (int i = 0; i < numberOfElement; i++) {
            String path=name+i;
            Image img= ResourcesLoader.getInstance().getImage("/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, false);
            images.add(img);
        }
    }


    public Image getDefaultImage() {
        return images.get(0);
    }

    public Image update() {

        index = (index+1) % images.size();
        return images.get(index);
    }
    public Image getCurrentImage(){
        return images.get(index);
    }




}
