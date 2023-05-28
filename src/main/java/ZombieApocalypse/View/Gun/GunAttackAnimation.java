package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.ArrayList;

public class GunAttackAnimation {
    //Carica le animazioni richieste

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    public GunAttackAnimation(String action, int numberOfElement) {
        for (int i = 0; i < numberOfElement; i++) {
            String path=action+i;
            Image img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
            images.add(img);
        }
    }



    public Image getDefaultImage() {
        return images.get(0);
    }

    public Image update() {
        if(index==images.size()-1){
            if(Game.getInstance().hasKnife)
                Game.getInstance().getKnifeModel().stopAttack();
            if(Game.getInstance().hasPistol)
                Game.getInstance().getGunModel().stopAttack();
            if( Game.getInstance().hasShotgun)
                Game.getInstance().getShotgunModel().stopAttack();
        }

        index = (index+1) % images.size();
        return images.get(index);
    }
}

