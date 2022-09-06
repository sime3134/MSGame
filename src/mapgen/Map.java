package mapgen;

import core.ContentManager;
import core.Game;
import metrics.Vector2D;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    Tile[][] map;

    private int mapWidth;
    private int mapHeight;
    private int maxNodes = 8;

    private ArrayList<Integer> newNodes;
    private int newNodePos;

    public Map(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
        map = new Tile[height][width];
        newNodes = new ArrayList<>();
        this.generateMap();
    }

    private Vector2D gridToPos(int row, int col) {
        return new Vector2D((float) col * Game.tileSize, (float) row * Game.tileSize);
    }

    private void generateMap() {
        // Set all tiles to basic tiles
        for (int row = 0; row < mapHeight; row++){
            for (int col = 0; col < mapWidth; col++) {
                map[row][col] = new Tile(gridToPos(row, col));
            }
        }

        // Change this to something random
        int blockWidth = 10;

        // Generating start node
        map[mapHeight/2][0] = new Tile(ContentManager.getSprite("checker"), gridToPos(mapHeight/2, 0),true);
        newNodes.add(mapHeight/2);
        newNodePos = 0;

        while(newNodePos + blockWidth < this.mapWidth) {
            int oldNodePos = newNodePos;
            newNodePos = oldNodePos + blockWidth;

            // Change this to something random
            int noNodes = 3;

            ArrayList<Integer> oldNodes = newNodes;
            newNodes = new ArrayList<Integer>();
            for (int i = 0; i < noNodes; i++) {
                int vertPos = i*mapHeight/noNodes + mapHeight/(noNodes*2);
                newNodes.add(vertPos);
                map[vertPos][newNodePos] = new Tile(ContentManager.getSprite("checker"), gridToPos(vertPos, newNodePos),true);
                // Connect the old node to the new one
            }
        }
    }

    private void createPaths(ArrayList<Integer> leftNodes, ArrayList<Integer> rightNodes, int leftX, int leftY) {
        
    }
    public Tile[][] getMap() {
        return this.map;
    }

}
