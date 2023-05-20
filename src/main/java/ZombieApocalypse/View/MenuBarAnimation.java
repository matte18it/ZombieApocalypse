package ZombieApocalypse.View;

import javax.swing.*;
import java.awt.*;

public class MenuBarAnimation {
    private static int numImmagini=3;
    ImageIcon[] images=new ImageIcon[numImmagini];


    public MenuBarAnimation(){
        Image logoS=null;
        try{
    for(int i=0; i<numImmagini; i++) {
        images[i] = new ImageIcon(getClass().getResource("/BarraDistatoeMenu/Icon"+i+".png"));
        Image im = images[i].getImage();
        if(i==2)
            logoS = im.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        else
            logoS = im.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        images[i] = new ImageIcon(logoS);


    }
    }catch (NullPointerException e){
    }}


    public ImageIcon setFullHeart() {
        return images[0];
    }
    public ImageIcon setEmptyHeart() {
        return images[1];
    }
    public ImageIcon setEmptySlot() {
        return images[2];
    }
}
