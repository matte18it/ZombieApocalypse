package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditorController {
    private final EditorView view;
    private EditorModel model;

    public EditorController(EditorView view, EditorModel model) {
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

    public void addListener(){}
}
