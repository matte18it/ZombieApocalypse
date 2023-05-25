package ZombieApocalypse;

import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ResourcesLoader {
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
                System.exit(100);

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
            System.exit(101);
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
        } catch (IOException  | IllegalArgumentException ex){ System.exit(102); }
        return  image;
    }
    public Clip getAudioClip(String path){
        AudioInputStream audioIn;
        Clip clip;
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
            System.exit(103);
        } return clip;

    }
    public BufferedImage rotateImage(BufferedImage imageToRotate, double degrees) {

        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(degrees), widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
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


        } catch (IOException  | IllegalArgumentException ex){
            System.exit(102);
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
        Image image = ResourcesLoader.getInstance().getImage(path, 32, 32, false);
        return toolkit.createCustomCursor(image , new Point(p1.getX(),  p1.getY()), "img");
    }

}
