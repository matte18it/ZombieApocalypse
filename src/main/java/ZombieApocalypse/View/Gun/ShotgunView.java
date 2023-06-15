package ZombieApocalypse.View.Gun;

import ZombieApocalypse.Model.Game;

import java.awt.*;


public class ShotgunView {
        private final GunAnimation gunAnimation= new GunAnimation("Fucile",4, GunAnimation.GunType.SHOTGUN);
        public Image currentImage;


        public ShotgunView() {
                currentImage=gunAnimation.getDefaultImage();

        }

        public void update(Point point) {

            Game.getInstance().getShotgun().update(point);

            //Aggiorno immagine
                currentImage=gunAnimation.update(Game.getInstance().getShotgun().getAngle());




        }
        public Image getCurrentImage() {
            return currentImage;
        }
    }

