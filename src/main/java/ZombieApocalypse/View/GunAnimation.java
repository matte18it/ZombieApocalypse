package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.util.ArrayList;

public class GunAnimation {

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;
    enum gun{UP,DOWN, LEFT,RIGHT};

    public GunAnimation(String action, int numberOfElements) {
        Image img=null;
        for (int i=0; i<gun.values().length; i++) {
            String path=action+i;
            if(i==gun.UP.ordinal() || i==gun.DOWN.ordinal())
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGunModel().getHeight(), Game.getInstance().getGunModel().getWidth(), true);
            else
            img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGunModel().getWidth(), Game.getInstance().getGunModel().getHeight(), true);
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

