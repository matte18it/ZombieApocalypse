package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ThreadPool;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class ShotgunView {
        private final GunAnimation gunAnimation= new GunAnimation("Fucile",4, GunAnimation.GunType.SHOTGUN);
        public Image currentImage;


        public ShotgunView() {
                currentImage=gunAnimation.getDefaultImage();

        }

        public void update(Point point) {

            Game.getInstance().getShotgunModel().update(point);

            //Aggiorno immagine
                currentImage=gunAnimation.update(Game.getInstance().getShotgunModel().angle);




        }
        public Image getCurrentImage() {
            return currentImage;
        }
    }

