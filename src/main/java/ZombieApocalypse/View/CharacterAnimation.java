package ZombieApocalypse.View;

import ZombieApocalypse.Model.PlayerCharacter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CharacterAnimation {
    //Carica le animazioni richieste

    private final ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    public CharacterAnimation(String action, int numberOfElements) {
        try {
            for (int i = 0; i < numberOfElements; i++) {
                Image img = ImageIO.read(getClass().getResourceAsStream("/Player/" + action + i + ".png"));
                images.add(img);
            }
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public Image getDefaultImage() {
        return images.get(0);
    }

    public Image update() {
        index = (index+1) % images.size();
        return images.get(index);
    }
}
