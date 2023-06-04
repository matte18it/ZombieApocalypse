package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;
import java.util.ArrayList;

public class ItemAnimation {

    private final ArrayList<Image> images = new ArrayList<>();

    private int index = 0;



    public ItemAnimation(String action, int numberOfElement) {
        Image img=null;
        for (int i=0; i<numberOfElement; i++) {
            String path=action+i;
            if(Game.getInstance().hasPistol)
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getPistolModel().getWidth(), Game.getInstance().getPistolModel().getHeight(), true);
            if(Game.getInstance().hasGrenade)
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGrenadeModel().getWidth(),  Game.getInstance().getGrenadeModel().getHeight(), true);
            if(Game.getInstance().hasKnife)
                img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getKnifeModel().getWidth(),  Game.getInstance().getKnifeModel().getHeight(), true);

            images.add(img);
        }

    }


    public Image update() {
        if(Game.getInstance().getPlayerCharacter().dir== PlayerCharacter.movementDirection.DOWN){
            index=3;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== PlayerCharacter.movementDirection.UP){
            index=2;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== PlayerCharacter.movementDirection.LEFT){
            index=1;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== PlayerCharacter.movementDirection.RIGHT){
            index=0;
            return images.get(index);}
        return null;
    }

    public Image getDefaultImage() {
        return images.get(0);
    }
}

