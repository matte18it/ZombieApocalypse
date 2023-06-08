package ZombieApocalypse.Controller;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.Editor.EditorBarView;

import java.awt.*;
import java.awt.event.*;

public class EditorBarController {
    private final EditorBarView view;

    public EditorBarController(EditorBarView view) {
        this.view = view;
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
                if(view.getTxtName().getText().equals("Insert map name") || view.getTxtName().getText().equals("Inserisci il nome della mappa") )
                    view.getTxtName().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(view.getTxtName().getText().equals(""))
                    if(GameData.lang.equals(GameData.Language.EN))
                        view.getTxtName().setText("Insert map name:");
                    else if(GameData.lang.equals(GameData.Language.IT))
                        view.getTxtName().setText("Inserisci il nome della mappa:");
            }
        });
    }

}
