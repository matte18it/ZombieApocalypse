package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.util.ArrayList;

public class GunAnimation {

    private final ArrayList<Image> images = new ArrayList<>();
    private final ArrayList<Image> attackImages = new ArrayList<>();
    private int index = 0;
    private int index2 = 0;

    public Image updateAttack() {
        if(index2==attackImages.size()-1)
            Game.getInstance().getGunModel().stopAttack();
        index2 = (index2+1) % attackImages.size();
        return attackImages.get(index2);
    }

    enum gun{UP,DOWN, LEFT,RIGHT};

    public GunAnimation(String action, boolean t){
        Image img=null;
        for (int i=0; i<gun.values().length; i++) {
            String path=action+i;
            img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/Animazione"+path+".png", Game.getInstance().getGunModel().getHeight(), Game.getInstance().getGunModel().getWidth(), true);
            attackImages.add(img);
        }

    }
    public GunAnimation(String action) {
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

