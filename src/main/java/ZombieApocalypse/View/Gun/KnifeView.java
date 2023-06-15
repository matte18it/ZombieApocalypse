package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KnifeView {
    private final ItemAnimation gunAnimation=new ItemAnimation("Coltello",4);
    private final GunAttackAnimation attackFrame=new GunAttackAnimation("AnimazioneColtello",4);
    private final GunAttackAnimation attackFrameReverse=new GunAttackAnimation("AnimazioneColtelloReverse",4);
    public Image currentImage;
    public KnifeView() {
            currentImage=gunAnimation.getDefaultImage();
    }
    public void update() {
        Game.getInstance().getKnifeModel().update();
            //Aggiorno immagine
            if( Game.getInstance().getKnifeModel().getAttack() ) {
                Game.getInstance().getKnifeModel().updateAttack();
                if(Game.getInstance().getPlayer().getDir()== Settings.movementDirection.UP)
                    currentImage=attackFrameReverse.update();
                else
                    currentImage = attackFrame.update();
                if(Game.getInstance().getKnifeModel().countAttack==3){
                    Game.getInstance().getKnifeModel().stopAttack();
                }
            } else
                currentImage=gunAnimation.update();
    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
