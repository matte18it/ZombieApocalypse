package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;

import java.awt.*;

public class KnifeView {
    private final ItemAnimation gunAnimation;
    private final GunAttackAnimation attackFrame;
    private final GunAttackAnimation attackFrameReverse;
    public Image currentImage;


    public KnifeView() {
        gunAnimation= new ItemAnimation("Coltello",4);
        attackFrame= new GunAttackAnimation("AnimazioneColtello",4);
        attackFrameReverse= new GunAttackAnimation("AnimazioneColtelloReverse",4);
        currentImage=gunAnimation.getDefaultImage();
    }

    public void update() {
        Game.getInstance().getKnifeModel().update();

        //Aggiorno immagine
        if( Game.getInstance().getKnifeModel().getAttack() ) {
            if(Game.getInstance().getPlayerCharacter().dir== PlayerCharacter.movementDirection.UP)
                currentImage=attackFrameReverse.update();
            else
                currentImage = attackFrame.update();
        } else
            currentImage=gunAnimation.update();

    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
