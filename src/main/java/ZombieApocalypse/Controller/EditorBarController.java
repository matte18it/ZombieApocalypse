package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.Editor.EditorBarModel;
import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;
import ZombieApocalypse.View.GameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EditorBarController {
    private final EditorBarView view;
    private EditorView editorView;
    private EditorBarModel model;

    public EditorBarController(EditorBarView view, EditorView editorView, EditorBarModel model) {
        this.editorView = editorView;
        this.view = view;
        this.model = model;
        view.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
            }
        });
    }

    public void addListener(){
        view.getArrow1().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.removeAll();
                view.Page2();
                view.revalidate();
                view.repaint();
            }
        });

        view.getArrow2().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.removeAll();
                view.Page1();
                view.revalidate();
                view.repaint();
            }
        });

        view.getTxtName().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(view.getTxtName().getText().equals("Insert map name:") || view.getTxtName().getText().equals("Inserisci il nome della mappa:") ){
                    view.getTxtName().setText("");
                    view.getTxtName().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(view.getTxtName().getText().equals(""))
                    if(GameData.lang.equals(GameData.Language.EN)){
                        view.getTxtName().setText("Insert map name:");
                        view.getTxtName().setForeground(new Color(156, 156, 156));
                    }
                    else if(GameData.lang.equals(GameData.Language.IT)){
                        view.getTxtName().setText("Inserisci il nome della mappa:");
                        view.getTxtName().setForeground(new Color(156, 156, 156));
                    }
            }
        });

        view.getLine().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(editorView.line){
                    editorView.line = false;
                    editorView.repaint();
                }
                else {
                    editorView.line = true;
                    editorView.repaint();
                }
            }
        });

        view.getExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                showDialog();
            }
        });

        view.getReset().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                editorView.line = true;
                editorView.initWorld();
                editorView.repaint();
            }
        });

        view.getSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!view.getTxtName().getText().equals("Insert map name:") && !view.getTxtName().getText().equals("Inserisci il nome della mappa") && !view.getTxtName().getText().equals(""))
                    model.saveMap(view.getTxtName().getText());
                else{
                    if(GameData.lang.equals(GameData.Language.IT)){
                        view.getTxtName().setText("Inserisci il nome della mappa:");
                        view.getTxtName().setForeground(Color.red);
                    }
                    else{
                        view.getTxtName().setText("Insert map name:");
                        view.getTxtName().setForeground(Color.red);
                    }
                }
            }
        });
    }

    private void showDialog() {

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
            btnMenu = new JButton("Esci");
            btnGo = new JButton("Annulla");
            label.setText("<html>Uscendo tutti i dati non salvati verranno eliminati. Non potrai, quindi, recuperare eventuali mappe non salvate.<br>Sei sicuro di voler uscire?</html>");
        }
        else {
            btnMenu = new JButton("Exit");
            btnGo = new JButton("Cancel");
            label.setText("<html>Leaving unsaved data will delete it. You will not be able to recover any unsaved maps.<br>Are you sure you want to quit?</html>");
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
                Game.getInstance().setPause(false);
                dialog.dispose();
            }
        });
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                dialog.dispose();
                GameFrame.menuLaunch();
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

}
