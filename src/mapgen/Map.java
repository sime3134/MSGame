package mapgen;

import org.w3c.dom.Node;

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
        map = new Tile[width][height];
        this.generateMap();
    }

    private void generateMap() {
        // Set all tiles to basic tiles
        for (int y = 0; y < mapHeight; y++){
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = new Tile();
            }
        }

        // Change this to something random
        int blockWidth = 10;

        // Generating start node
        map[mapHeight/2][0] = new Tile(true);
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

                // Connect the old node to the new one
            }
        }




    }

}
