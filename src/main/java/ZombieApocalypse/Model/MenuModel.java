package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;

public class MenuModel {
    private MenuView view;
    private JButton btnPop;
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

    public void showDialog() {
        //carico il font
        Font font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        UIManager.put("OptionPane.background",new Color(92,75,35));
        UIManager.put("Panel.background",new Color(18,17,15));
        UIManager.put("OptionPane.minimumSize",new Dimension(500,200));
        UIManager.put("OptionPane.border", new EmptyBorder(10, 10, 10,10));
        UIManager.put("OptionPane.font", ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        UIManager.put("OptionPane.foreground", Color.WHITE);

        //creo la label e gli setto il font personalizzato
        JLabel layout = new JLabel();
        layout.setPreferredSize(new Dimension(200, 170));
        layout.setMaximumSize(new Dimension(200, 170));
        layout.setMinimumSize(new Dimension(200, 170));
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        JLabel label = new JLabel();
        if(GameData.lang.equals(GameData.Language.IT)) {
            label.setText("<html>Confermando tutti i dati locali relativi al profilo verranno eliminati e l'applicazione verrà chiusa. Tuttavia potrai accedere nuovamente al tuo account inserendo le tue credenziali di accesso.<br>Sei sicuro di voler effettuare il logout?</html>");
            btnPop = new JButton("Conferma");
        }
        else {
            label.setText("<html>Confirming all local profile data will be deleted and the application will be closed. However, you can access your account again by entering your login credentials.<br>Are you sure you want to log out?</html>");
            btnPop = new JButton("Confirm");
        }
        label.setFont(font);
        label.setBorder(new EmptyBorder(10, 10, 0, 0));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(200, 170));
        label.setPreferredSize(new Dimension(200, 170));
        layout.add(label);

        //qua creo il pulsante per la conferma e gli assegno un listener. Quando viene cliccato cancella i dati e chiude il gioco
        btnPop.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 197, 60, false));
        btnPop.setHorizontalTextPosition(JButton.CENTER);
        btnPop.setVerticalTextPosition(JButton.CENTER);
        btnPop.setBorderPainted(false);
        btnPop.setFocusPainted(false);
        btnPop.setContentAreaFilled(false);
        btnPop.setForeground(Color.WHITE);
        btnPop.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        btnPop.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                File myObj = new File("player.txt");
                myObj.delete();
                System.exit(0);
            }
        });

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane( layout,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnPop});
        JDialog dialog;
        if(GameData.lang.equals(GameData.Language.IT))
            dialog = pane.createDialog("ATTENZIONE");
        else
            dialog = pane.createDialog("DANGER");
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
