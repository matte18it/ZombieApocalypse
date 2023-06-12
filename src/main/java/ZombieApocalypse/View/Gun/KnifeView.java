package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KnifeView {
    private final Future<ItemAnimation> gunAnimation;
    private final Future<GunAttackAnimation> attackFrame;
    private final Future<GunAttackAnimation> attackFrameReverse;
    public Image currentImage;


    public KnifeView() {
        gunAnimation= ThreadPool.executeItemAnimation(new ItemAnimation("Coltello",4));
        attackFrame= ThreadPool.GunAttackAnimation(new GunAttackAnimation("AnimazioneColtello",4));
        attackFrameReverse= ThreadPool.GunAttackAnimation(new GunAttackAnimation("AnimazioneColtelloReverse",4));

        try {
            currentImage=gunAnimation.get().getDefaultImage();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }
    }

    public void update() {
        Game.getInstance().getKnifeModel().update();
        try {
            //Aggiorno immagine
            if( Game.getInstance().getKnifeModel().getAttack() ) {
                if(Game.getInstance().getPlayerCharacter().dir== Settings.movementDirection.UP)
                    currentImage=attackFrameReverse.get().update();
                else
                    currentImage = attackFrame.get().update();
            } else
                currentImage=gunAnimation.get().update();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            System.exit(207);
        }



    }
    public Image getCurrentImage() {
        return currentImage;
    }

}
