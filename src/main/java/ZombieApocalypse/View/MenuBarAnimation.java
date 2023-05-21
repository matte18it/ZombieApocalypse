package ZombieApocalypse.View;

import ZombieApocalypse.ResourcesLoader;

import javax.swing.*;
import java.awt.*;

public class MenuBarAnimation {
    private static int numImmagini=3;
    enum Icon{FULLHEART, EMPTYHEART, EMPTYSLOT};
    ImageIcon[] images=new ImageIcon[numImmagini];






    public ImageIcon setIcon(Icon i, int w, int h) {
        System.out.println(i.ordinal());
        if(images[i.ordinal()]==null){
            images[i.ordinal()]= ResourcesLoader.getInstance().getImageIcon("/BarraDistatoeMenu/Icon"+i.ordinal()+".png",w,h,true );
                }
        return images[i.ordinal()];
    }

}
