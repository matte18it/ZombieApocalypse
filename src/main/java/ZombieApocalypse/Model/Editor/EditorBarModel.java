package ZombieApocalypse.Model.Editor;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;
import ZombieApocalypse.View.GameFrame;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorBarModel {
    private EditorBarView view;
    private EditorView editorView;
    public EditorBarModel(EditorBarView view, EditorView editorView) {
        //Creo la view
        this.view = view;
        this.editorView = editorView;
    }
    
    public void saveMap(String name){
        //creo folder e file
        File f1 = new File("EditorMap");
        File f2 = new File("EditorMap/" + name + ".txt");

        //qua verifico se la directory e il file esistono, se non esistono li creo
        if(!f1.exists())
            f1.mkdir();
        if(!f2.exists()){
            try { f2.createNewFile(); }
            catch (IOException e) { throw new RuntimeException(e); }
            writeOnFile(f2);
        }
        else{
            //se il file esiste chiedo all'utente se lo vuole sovrascrivere
            showDialog(f2);
        }
    }

    private void writeOnFile(File f2) {
        //questa funzione si occupa solo di scrivere su file la mappa
        try {
            FileWriter myWriter;
            myWriter = new FileWriter("EditorMap/" + f2.getName());
            for (int i = 0; i < editorView.getWorldLength(); i++) {

                for (int j = 0; j < editorView.getWorldLengthRow(i); j++)
                    myWriter.write(editorView.getEnum(i, j) + " ");

                myWriter.write("\n");
            }
            myWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDialog(File f2) {
        //dialog per informare il player che il nome inserito è già associato ad una mappa.
        //continuando sovrascrive la mappa già presente

        Font font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        UIManager.put("OptionPane.background",new Color(92,75,35));
        UIManager.put("Panel.background",new Color(18,17,15));
        UIManager.put("OptionPane.minimumSize",new Dimension(500,200));
        UIManager.put("OptionPane.border", new EmptyBorder(10, 10, 10,10));
        UIManager.put("OptionPane.font", ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        UIManager.put("OptionPane.foreground", Color.WHITE);

        //creo la label e gli setto il font personalizzato
        JLabel label = new JLabel();
        label.setFont(font.deriveFont(Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));
        label.setBorder(new EmptyBorder(10, 10, 0, 0));

        JButton btnMenu;
        JButton btnGo;
        if(GameData.lang.equals(GameData.Language.IT)) {
            btnMenu = new JButton("Sovrascrivi");
            btnGo = new JButton("Annulla");
            label.setText("<html>Esiste già un file con questo nome.<br>Vuoi sovrascriverlo?</html>");
        }
        else {
            btnMenu = new JButton("Overwrite");
            btnGo = new JButton("Cancel");
            label.setText("<html>A file with this name already exists.<br>Do you want to overwrite it?</html>");
        }

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        btnGo.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnGo.setHorizontalTextPosition(JButton.CENTER);
        btnGo.setVerticalTextPosition(JButton.CENTER);
        btnGo.setBorderPainted(false);
        btnGo.setFocusPainted(false);
        btnGo.setContentAreaFilled(false);
        btnGo.setForeground(Color.WHITE);
        btnGo.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu, btnGo});
        JDialog dialog = new JDialog();
        btnGo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                Game.getInstance().setPause(false);
                dialog.dispose();
            }
        });
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                f2.delete();
                try { f2.createNewFile(); } catch (IOException ex) { throw new RuntimeException(ex); }
                writeOnFile(f2);
            }
        });

        dialog.getContentPane().add(pane);
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setSize(new Dimension(515, 220));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    public void clearBorder(){
        //pulisco tutti i bordi dei pulsanti che indicano i blocchi.
        //infatti l'utente può capire quale tassella sta usando anche perchè quella selezionata avrà un bordo rosso attorno

        view.getDirt0().setBorder(null);
        view.getDirt1().setBorder(null);
        view.getDirt2().setBorder(null);
        view.getDirt3().setBorder(null);
        view.getDivisorio().setBorder(null);
        view.getWater0().setBorder(null);
        view.getFlower1().setBorder(null);
        view.getFlower2().setBorder(null);
        view.getRoad1().setBorder(null);
        view.getRoad2().setBorder(null);
        view.getRoad3().setBorder(null);
        view.getRoad4().setBorder(null);
        view.getRoad5().setBorder(null);
        view.getRoad6().setBorder(null);
        view.getRoad7().setBorder(null);
        view.getRoad8().setBorder(null);
        view.getRoad9().setBorder(null);
        view.getRoad10().setBorder(null);
        view.getRoad11().setBorder(null);
        view.getRoad12().setBorder(null);
        view.getRoad13().setBorder(null);
        view.getRoad14().setBorder(null);
        view.getRoad15().setBorder(null);
        view.getRoad16().setBorder(null);
        view.getRoad17().setBorder(null);
        view.getRoad18().setBorder(null);
        view.getRoad19().setBorder(null);
        view.getRoad20().setBorder(null);
        view.getRoad21().setBorder(null);
        view.getRoad22().setBorder(null);
        view.getRoad23().setBorder(null);
        view.getRoad24().setBorder(null);
        view.getRoad25().setBorder(null);
    }

}
