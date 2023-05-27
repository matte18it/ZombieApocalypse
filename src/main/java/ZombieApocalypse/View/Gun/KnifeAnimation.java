package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Character;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResourcesLoader;

import java.awt.*;
import java.util.ArrayList;

public class KnifeAnimation {

    private final ArrayList<Image> images = new ArrayList<>();

    private int index = 0;



    public KnifeAnimation(String action, int numberOfElement) {
        Image img=null;
        for (int i=0; i<numberOfElement; i++) {
            String path=action+i;
            img= ResourcesLoader.getInstance().getImage("/ArmieOggetti/"+path+".png", Game.getInstance().getGunModel().getWidth(), Game.getInstance().getGunModel().getHeight(), true);
            images.add(img);
        }

    }


    public Image update() {
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.DOWN){
            index=3;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.UP){
            index=2;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.LEFT){
            index=1;
            return images.get(index);}
        if(Game.getInstance().getPlayerCharacter().dir== Character.movementDirection.RIGHT){
            index=0;
            return images.get(index);}
        return null;
    }

    public Image getDefaultImage() {
        return images.get(0);
    }
}

