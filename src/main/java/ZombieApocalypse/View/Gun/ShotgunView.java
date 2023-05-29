package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;

import java.awt.*;


    public class ShotgunView {
        private final GunAnimation gunAnimation;
        public Image currentImage;


        public ShotgunView() {
            gunAnimation= new GunAnimation("Fucile",4);
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
