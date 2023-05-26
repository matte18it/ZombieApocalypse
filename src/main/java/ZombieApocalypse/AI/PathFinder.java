package ZombieApocalypse.AI;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Model.World.*;
import ZombieApocalypse.Settings;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.GraphicPanel;

import java.util.ArrayList;




public class PathFinder {
    GameFrame gf;
    GraphicPanel gp;
    Node [][] node;
    public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    public Node startNode, goalNode, currentNode;
    public boolean goalReached = false;
    int step = 0;

    public PathFinder(GraphicPanel gp) {
        this.gp = gp;
        initNodes();
    }

    // Creiamo nodo per ogni tile della mappa
    public void initNodes(){
        node = new Node[Settings.WORLD_SIZEY][Settings.WORLD_SIZEX];

        int col = 0;
        int row = 0;
        while(col < Settings.WORLD_SIZEY && row < Settings.WORLD_SIZEX){
            node[col][row] = new Node(col, row);
            col++;
            if(col==Settings.WINDOW_SIZEY){
                col = 0;
                row++;
            }
        }

    }

    // Ogni volta che faccio pathfinding, resetto il risultato precedente
    public void resetNodes(){
        int col = 0;
        int row = 0;
        while(col < Settings.WORLD_SIZEY && row < Settings.WORLD_SIZEX){
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if(col==Settings.WINDOW_SIZEY){
                col = 0;
                row++;

            }
        }
    }

    public void resetLists() {
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();
        resetLists();

        // Set start node e end node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int row = 0;
        int col = 0;
        while(col < Settings.WORLD_SIZEY && row < Settings.WORLD_SIZEX){
            // Set solid node
            // Settiamo tileNum a col e row della mappa, poi si farà if tileNum.collision == true
            int tileNum = gp.numeroImmagini;




        }
    }
}
