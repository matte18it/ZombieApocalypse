package ZombieApocalypse.View;

import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.util.ArrayList;

public class GunAnimation {

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    public GunAnimation(String action, int numberOfElements) {
        Image img=null;
        for (int i = 0; i < numberOfElements; i++) {
            String path=action+i;
                if(i==2 || i==3)
                    img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Settings.CELL_SIZEY/2, Settings.CELL_SIZEX, true);
                else
                    img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY/2, true);
            images.add(img);
        }

    }


    public Image update(double angle) {
        if((angle<60 && angle>=0) || (angle>=320)){
            index=0;
            return images.get(index);
        }
        if(angle<140 && angle>=60){
            index=2;
            return images.get(index);

        }
        if(angle<230 && angle>=140){
            index=1;
            return images.get(index);

        }
        if(angle<360 && angle>=230){
            index=3;
            return images.get(index);

        }
        return null;
    }

    public Image getDefaultImage() {
        return images.get(0);
    }
}

