package ZombieApocalypse.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBackgroundPane extends JOptionPane {
    private BufferedImage img;
    private String message;

    public ImageBackgroundPane () {
        this.img = ResourcesLoader.getInstance().getBufferedImage("/SettingsImage/panePanel.png", 512, 256, false);
        this.setPreferredSize(new Dimension(512, 300));
        this.setMaximumSize(new Dimension(512, 300));
        this.setMinimumSize(new Dimension(512, 300));
        this.add(new JButton("CIAO"));
    }

    public void show(String message){
        JDialog d = this.createDialog("DANGER");
        d.setLayout(new BorderLayout());
        d.add(new JButton("CIOA"), BorderLayout.NORTH);
        //d.dispose();
        //d.setUndecorated(true);
        d.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension curSize = this.getSize();
        g.drawImage(img, 0, 0, curSize.width, curSize.height, null);
    }
}
