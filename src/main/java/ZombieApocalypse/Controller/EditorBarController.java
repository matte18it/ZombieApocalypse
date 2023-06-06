package ZombieApocalypse.Controller;

import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.Editor.EditorBarView;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

    }

}
