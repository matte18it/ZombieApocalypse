package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class KnifeView {
    //Gestione del knife
    private final ItemAnimation gunAnimation=new ItemAnimation("Coltello",4);
    private final GunAttackAnimation attackFrame=new GunAttackAnimation("AnimazioneColtello",4);
    private final GunAttackAnimation attackFrameReverse=new GunAttackAnimation("AnimazioneColtelloReverse",4);
    public Image currentImage;
    public KnifeView() {
            currentImage=gunAnimation.getDefaultImage();
    }
    public void update() {
        Game.getInstance().getKnife().update();
            //Aggiorno immagine, reverse è l'immagine capovolta
            if( Game.getInstance().getKnife().getAttack() ) {
                Game.getInstance().getKnife().updateAttack();
                if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP)
                    currentImage=attackFrameReverse.update();
                else
                    currentImage = attackFrame.update();
                if(Game.getInstance().getKnife().getCountAttack()==3){
                    Game.getInstance().getKnife().stopAttack();
                }
            } else
                currentImage=gunAnimation.update();
    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
