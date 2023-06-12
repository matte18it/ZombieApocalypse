package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class ShotgunView {
        private final Future<GunAnimation> gunAnimation;
        public Image currentImage;


        public ShotgunView() {
            gunAnimation= ThreadPool.executeGunAnimation(new GunAnimation("Fucile",4, GunAnimation.GunType.SHOTGUN));

            try {
                currentImage=gunAnimation.get().getDefaultImage();
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
                System.exit(207);
            }
        }

        public void update(Point point) {

            Game.getInstance().getShotgunModel().update(point);

            //Aggiorno immagine
            try {
                currentImage=gunAnimation.get().update(Game.getInstance().getShotgunModel().angle);

            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
                System.exit(207);
            }


        }
        public Image getCurrentImage() {
            return currentImage;
        }
    }

