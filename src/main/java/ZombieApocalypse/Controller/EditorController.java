package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;

import java.awt.*;
import java.awt.event.*;

public class EditorController {
    private final EditorView view;
    private EditorModel model;

    public EditorController(EditorView view, EditorModel model) {
        this.view = view;
        this.model = model;
        addListener();
    }

    private void customCursor() {
        //qua setto il cursore personalizzato in base alla tassella scelta dal player
        if(EditorBarView.bloccoAttivo == -1)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 0)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore2.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 1)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore3.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 2)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore4.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 3)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore5.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 4)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore1.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 5)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore6.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 6)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore7.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 7)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore8.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 8)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore9.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 9)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore10.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 10)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore11.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 11)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore12.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 12)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore13.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 13)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore14.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 14)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore15.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 15)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore16.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 16)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore17.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 17)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore18.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 18)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore19.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 19)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore20.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 20)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore21.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 21)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore22.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 22)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore23.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 23)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore24.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 24)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore25.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 25)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore26.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 26)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore27.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 27)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore28.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 28)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore29.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 29)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore30.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 30)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore31.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 31)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore32.png", 32, 32, false), new Point(20, 20), "Cursor"));
        else if(EditorBarView.bloccoAttivo == 32)
            view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/Cursori/Cursore33.png", 32, 32, false), new Point(20, 20), "Cursor"));

    }

    public void addListener(){
        //eventi per disegnare
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                customCursor();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX(), y = e.getY(), x1, y1;

                for(int i = 0; i < view.getWorldLength(); i++) {
                    x1 = i * Settings.CELL_SIZEX;
                    for(int j = 0; j < view.getWorldLengthRow(i); j++) {
                        y1 = j * Settings.CELL_SIZEY;
                        if((x >= x1 && x <= x1 + Settings.CELL_SIZEX) && (y >= y1 && y <= y1 + Settings.CELL_SIZEY))
                            view.setTassello(i, j);
                    }
                }

            }
        });
    }
}
