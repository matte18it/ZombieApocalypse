package ZombieApocalypse;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

public class FontLoad {
    private static final FontLoad instance = new FontLoad();
    private FontLoad() {
    }
    public static FontLoad getInstance() {
        return instance;
    }

    public Font getPixelFont() {
        Font font=null;
            try{
                font = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("/font/PixelFont.otf"))).deriveFont(Font.PLAIN,20);
            }catch (IOException | FontFormatException e){
                e.printStackTrace();}
            return font;
    }
}
