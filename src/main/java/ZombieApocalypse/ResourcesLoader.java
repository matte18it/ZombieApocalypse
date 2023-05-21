package ZombieApocalypse;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

public class ResourcesLoader {
    private static final ResourcesLoader instance = new ResourcesLoader();
    private ResourcesLoader() {
    }
    public static ResourcesLoader getInstance() {
        return instance;
    }

    public Font getFont(String path, int size, int type) {
        Font font=null;
            try{
                font = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream(path))).deriveFont(type,size);
            }catch (IOException | FontFormatException e){
                e.printStackTrace();}
            return font;
    }
    public ImageIcon getImageIcon(String name, int width, int height, boolean b){
        ImageIcon image=null;
        Image logoS=null;
        try{
                image = new ImageIcon(getClass().getResource(name));
                Image im = image.getImage();
                if(b)
                    logoS = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                else
                    logoS = im.getScaledInstance(width, height, Image.SCALE_FAST);
            image = new ImageIcon(logoS);

        }catch (NullPointerException e){
        } return image;
    }

    public Image getImage(String name, int width, int height, boolean b){
        Image image=null;
        try{
            image= ImageIO.read(getClass().getResourceAsStream(name));

            image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);


    } catch (IOException  | IllegalArgumentException ex){
        System.exit(1);
    }
        return  image;
}
    public Clip getAudioClip(String path){
        AudioInputStream audioIn;
        Clip clip;
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/" + name));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
        } return clip;

    }

}
