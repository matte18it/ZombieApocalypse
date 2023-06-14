package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.ArrayList;

public class GunAttackAnimation {
    //Carica le animazioni richieste

    private final ArrayList<ThreadPool> images = new ArrayList<>();
    private int index = 0;

    public GunAttackAnimation(String action, int numberOfElement) {
        ThreadPool temp=null;
        for (int i = 0; i < numberOfElement; i++) {
            String path=action+i;
            temp=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, true));
            images.add(temp);
        }
    }



    public Image getDefaultImage() {
        return images.get(0).get();
    }

    public Image update() {
        if(index==images.size()-1){
            if(Game.getInstance().hasKnife)
                Game.getInstance().getKnifeModel().stopAttack();
            if(Game.getInstance().hasPistol)
                Game.getInstance().getPistolModel().stopAttack();
            if( Game.getInstance().hasShotgun)
                Game.getInstance().getShotgunModel().stopAttack();
        }

        index = (index+1) % images.size();
        return images.get(index).get();
    }
}

