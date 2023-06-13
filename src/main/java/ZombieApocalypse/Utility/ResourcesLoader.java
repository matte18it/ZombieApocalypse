package ZombieApocalypse.Utility;

import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ResourcesLoader {
    String resourcesError="Errore nella lettura delle Risorse   ";

    private static final ResourcesLoader instance = new ResourcesLoader();
    private ResourcesLoader() {}
    public static ResourcesLoader getInstance() {
        return instance;
    }
    public Font getFont(String path, int size, int type) {
        Font font=null;
            try{
                font = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream(path))).deriveFont(type,size);
            }catch (IOException | FontFormatException e){
                ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 100);
            }
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
            ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 101);
        } return image;
    }
    public Image getImage(String name, int width, int height, boolean b){
        Image image=null;
        try{
            image= ImageIO.read(getClass().getResourceAsStream(name));
            if(b)
            image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            else
                image=image.getScaledInstance(width, height, Image.SCALE_FAST);
        } catch (IOException  | IllegalArgumentException e){
            ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 102); }
        return  image;
    }
    public Clip getAudioClip(String path){
        AudioInputStream audioIn;
        Clip clip=null;
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 103);

        } return clip;

    }

    public BufferedImage getBufferedImage(String s, int width, int height, boolean b) {
        BufferedImage image=null;
        BufferedImage dimg=null;
        try{
            image= ImageIO.read(getClass().getResourceAsStream(s));
            Image tmp=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            dimg=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();


        } catch (IOException  | IllegalArgumentException e){
            ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 104);
        }
        return  dimg;
    }


    public int getHours() {
        DateTime date = new DateTime();
        if(date.getHourOfDay() >= 6 && date.getHourOfDay() <= 15)
            return 1;
        else if(date.getHourOfDay() >= 16 && date.getHourOfDay() <= 19)
            return 3;
        else if(date.getHourOfDay() >= 20 && date.getHourOfDay() <= 22)
            return 2;
        else
            return 4;
    }

    public Cursor getCursor(String path, JPanel p1){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor=null;
        Image image = ResourcesLoader.getInstance().getImage(path, 32, 32, false);
        try {
            cursor=toolkit.createCustomCursor(image, new Point(p1.getX(), p1.getY()), "img");
        }catch (IndexOutOfBoundsException | HeadlessException e){
            ResultsPanel.getInstance().showError(resourcesError+ e.getMessage(), 105);
        } return cursor;

    }



}
