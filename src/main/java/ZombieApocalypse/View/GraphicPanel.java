package ZombieApocalypse.View;

import ZombieApocalypse.Model.World;
import ZombieApocalypse.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class GraphicPanel extends JPanel {
    //Disegna il mondo
    //private final CharacterView characterView = new CharacterView();
    private final int numeroImmagini=4;
    private final Image[] images=new Image[numeroImmagini];
    public GraphicPanel()  {
        try{
        for(int i=0; i<numeroImmagini; i++){
            String c=String.valueOf(i);
            images[i]= ImageIO.read(getClass().getResourceAsStream("/AmbienteDiGioco/Terreno/Terreno"+c+".png"));

            images[i]=images[i].getScaledInstance(Settings.CELL_SIZE, Settings.CELL_SIZE, Image.SCALE_SMOOTH);

        }} catch (IOException e){
            System.exit(1);
    }

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        World world=new World();
        for(int i = 0; i < world.getSize(); i++) {
            int x = i * Settings.CELL_SIZE;
            for(int j = 0; j < world.getSize(); j++) {
                int y = j * Settings.CELL_SIZE;
                if(world.isGround(i, j)) {
                    Random random=new Random();
                    int value= random.nextInt(numeroImmagini);
                    g.drawImage(images[value], x, y, null);
                }

                }


    }}}

