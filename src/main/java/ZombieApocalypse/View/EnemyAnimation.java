package ZombieApocalypse.View;

import ZombieApocalypse.Model.Enemy;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;
import jdk.jfr.SettingControl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyAnimation {
    //Carica le animazioni richieste

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    public EnemyAnimation(String action, int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            String path=action+i;
            Image img= ResourcesLoader.getInstance().getImage("/Zombie1/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
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
}

