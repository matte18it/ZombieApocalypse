package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorBarController;
import ZombieApocalypse.Controller.EditorController;
import ZombieApocalypse.Utility.ResourcesLoader;

import javax.swing.*;
import java.awt.*;

public class EditorView extends JPanel {
    private EditorController controller;

    public EditorView(){
        //setto il cursore
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

        //creo il controller
        controller = new EditorController(this);
    }
}
