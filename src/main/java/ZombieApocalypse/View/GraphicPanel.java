package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GraphicPanel extends JPanel {
    //Disegna il mondo
    private final CharacterView characterView = new CharacterView();
    private final MenuBarView menuBarView=new MenuBarView();
    private final int numeroImmagini=4;
    private final Image[] images=new Image[numeroImmagini];
    public GraphicPanel()  {
        try{
        for(int i=0; i<numeroImmagini; i++){
            String c=String.valueOf(i);
            images[i]= ImageIO.read(getClass().getResourceAsStream("/AmbienteDiGioco/Terreno/Terreno"+c+".png"));

            images[i]=images[i].getScaledInstance((int)Settings.CELL_SIZEX, (int)Settings.CELL_SIZEY, Image.SCALE_SMOOTH);

        }} catch (IOException e){
            System.exit(1);
    }

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        World world=new World();
        for(int i = 0; i < world.getSize(); i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.getSize(); j++) {
                int y = j * Settings.CELL_SIZEY;
                if(world.isGround0(i, j)) {
                    //Random random=new Random();
                    //int value= random.nextInt(numeroImmagini);
                    g.drawImage(images[0], x, y, null);
                }

                }


    }
        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayerCharacter().getX(), Game.getInstance().getPlayerCharacter().getY(), characterView.width, characterView.height, null);

        }
    public void update() {
        characterView.update();
        if(Game.getInstance().getPlayerCharacter().getHit())   //Esempio di update della Barra
            menuBarView.update();
        repaint();
    }}

