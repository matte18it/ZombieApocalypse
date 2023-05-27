package ZombieApocalypse.View.MenuBar;

import ZombieApocalypse.Utility.ResourcesLoader;

import javax.swing.*;

public class MenuBarAnimation {
    private static int numImmagini=3;
    enum Icon{FULLHEART, EMPTYHEART, EMPTYSLOT};
    ImageIcon[] images=new ImageIcon[numImmagini];






    public ImageIcon setIcon(Icon i, int w, int h) {
        if(images[i.ordinal()]==null){
            images[i.ordinal()]= ResourcesLoader.getInstance().getImageIcon("/BarraDistatoeMenu/Icon"+i.ordinal()+".png",w,h,true );
                }
        return images[i.ordinal()];
    }

}
