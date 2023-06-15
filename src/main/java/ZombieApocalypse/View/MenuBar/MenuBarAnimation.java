package ZombieApocalypse.View.MenuBar;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.ResourcesLoader;
import javax.swing.*;

public class MenuBarAnimation {
    //Caricamento delle immagini nella menu bar (cuori e slot)
    enum Icon{ FULLHEART, EMPTYHEART}
    ImageIcon[] images=new ImageIcon[Items.ItemType.values().length];
    ImageIcon[] hearts=new ImageIcon[2];
    public ImageIcon setIcon(Icon i, int w, int h) {
        if(hearts[i.ordinal()]==null){
            hearts[i.ordinal()]= ResourcesLoader.getInstance().getImageIcon("/BarraDistatoeMenu/"+i+".png",w,h,true );
                }
        return hearts[i.ordinal()];
    }
    public ImageIcon setIcon(Items.ItemType i, int w, int h) {
        if(images[i.ordinal()]==null){
            images[i.ordinal()]= ResourcesLoader.getInstance().getImageIcon("/BarraDistatoeMenu/"+i+".png",w,h,true );
        }
        return images[i.ordinal()];
    }

}
