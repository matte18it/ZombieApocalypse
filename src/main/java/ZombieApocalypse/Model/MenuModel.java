package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class MenuModel {
    private MenuView view;
    public MenuModel(MenuView view) {
        //Creo la view
        this.view = view;
    }

    public void translateSettings() {
        if(GameData.lang== GameData.Language.IT){
            view.getMusic().setText("Volume Musica: ");
            view.getSound().setText("Volume Suoni: ");
            view.getLanguage().setText("Lingua: ");
            view.getSettingsLabel().setText("IMPOSTAZIONI");
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
            view.getSettingsLabel().setBorder(new EmptyBorder(25, ((830/2)-((int)(view.getFont().deriveFont(Font.PLAIN, 30).getStringBounds(view.getSettingsLabel().getText(), frc).getWidth())/2))+25, 0, 0));
            view.getMancino().setText("Mancino: ");
        }
        else{
            view.getMusic().setText("Music Volume: ");
            view.getSound().setText("Sound Volume: ");
            view.getLanguage().setText("Language: ");
            view.getSettingsLabel().setText("SETTINGS");
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
            view.getSettingsLabel().setBorder(new EmptyBorder(25, ((830/2)-((int)(view.getFont().deriveFont(Font.PLAIN, 30).getStringBounds(view.getSettingsLabel().getText(), frc).getWidth())/2))+25, 0, 0));
            view.getMancino().setText("Left Handed: ");
        }
    }
}
