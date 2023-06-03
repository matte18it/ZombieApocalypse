package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;
import java.util.ArrayList;

public class GunAnimation {

    private final ArrayList<Image> images = new ArrayList<>();

    private int index = 0;
    enum GunType{SHOTGUN, PISTOL};



    public GunAnimation(String action, int numberOfElement, GunType e) {
        Image img=null;
        for (int i=0; i<numberOfElement; i++) {
            String path=action+i;
            if(e==GunType.SHOTGUN)
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getShotgunModel().getWidth(),  Game.getInstance().getShotgunModel().getHeight(),  true);
            if(e==GunType.PISTOL)
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getPistolModel().getWidth(),  Game.getInstance().getPistolModel().getHeight(),  true);
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
        return getDefaultImage();
    }

    public Image getDefaultImage() {
        return images.get(0);
    }
}

