package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.ArrayList;

public class ItemAnimation {

    private final ArrayList<ThreadPool> images = new ArrayList<>();

    private int index = 0;



    public ItemAnimation(String action, int numberOfElement) {
        ThreadPool temp=null;
        for (int i=0; i<numberOfElement; i++) {
            String path=action+i;
            if(Game.getInstance().hasPistol)
                temp=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getPistolModel().getWidth(), Game.getInstance().getPistolModel().getHeight(), true));
            if(Game.getInstance().hasGrenade)
                temp=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGrenadeModel().getWidth(),  Game.getInstance().getGrenadeModel().getHeight(), true));
            if(Game.getInstance().hasKnife)
                temp=new ThreadPool(ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getKnifeModel().getWidth(),  Game.getInstance().getKnifeModel().getHeight(), true));

            images.add(temp);
        }

    }


    public Image update() {
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.DOWN){
            index=3;
            return images.get(index).get();}
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.UP){
            index=2;
            return images.get(index).get();}
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.LEFT){
            index=1;
            return images.get(index).get();}
        if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.RIGHT){
            index=0;
            return images.get(index).get();}
        return null;
    }

    public Image getDefaultImage() {
        return images.get(0).get();
    }
}

