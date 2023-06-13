package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.ArrayList;

public class GunAnimation  {

    private final ArrayList<ThreadPool> images = new ArrayList<>();

    private int index = 0;
    enum GunType{SHOTGUN, GRENADE, PISTOL};



    public GunAnimation(String action, int numberOfElement, GunType e) {
        ThreadPool instance=null;
        for (int i=0; i<numberOfElement; i++) {
            String path=action+i;
            if(e==GunType.SHOTGUN)
                instance= new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getShotgunModel().getWidth(),  Game.getInstance().getShotgunModel().getHeight(),  true));
            if(e==GunType.PISTOL)
                instance= new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getPistolModel().getWidth(),  Game.getInstance().getPistolModel().getHeight(),  true));
            if(e==GunType.GRENADE)
                instance=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGrenadeModel().getWidth(),  Game.getInstance().getGrenadeModel().getHeight(), true));
            images.add(instance);

    }}



    public Image update(double angle) {
        if((angle<60 && angle>=0) || (angle>=320)){
            index=0;
            return images.get(index).get();
        }
        if(angle<140 && angle>=60){
            index=2;
            return images.get(index).get();

        }
        if(angle<230 && angle>=140){
            index=1;
            return images.get(index).get();

        }
        if(angle<360 && angle>=230){
            index=3;
            return images.get(index).get();

        }
        return getDefaultImage();
    }

    public Image getDefaultImage() {
        return images.get(0).get();
    }
}

