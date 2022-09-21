package mapgen;

import core.ContentManager;
import core.Game;
import gfx.Camera;
import metrics.Vector2D;
import org.w3c.dom.Node;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;

public class Map {
    Tile[][] map;

    private int mapWidth;
    private int mapHeight;
    private int maxNodes = 8;
    private int noBlocks = 3;
    private int blocksDeleted = 0;

    private ArrayList<Integer> newNodes;
    private int newNodePos;

    public Map(int width, int height, Camera camera) {
        this.mapWidth = width;
        this.mapHeight = height;
        map = new Tile[height][width*noBlocks];
        newNodes = new ArrayList<>();
        this.generateMap(camera);
    }

    private Vector2D gridToPos(int row, int col) {

        return new Vector2D((float) (col+blocksDeleted*mapWidth) * Game.tileSize, (float) row * Game.tileSize);
    }

    private Vector2D posToGrid(float x, float y) {
        return new Vector2D((int) y / Game.tileSize, (int) x / Game.tileSize - blocksDeleted*mapWidth);
    }

    private void generateMap(Camera camera) {
        // Set all tiles to basic tiles
        for (int row = 0; row < mapHeight; row++){
            for (int col = 0; col < mapWidth*noBlocks; col++) {
                map[row][col] = new Tile(gridToPos(row, col));
            }
        }
        // Generating start node
        map[mapHeight/2][0] = new Tile(ContentManager.getSprite("checker"), gridToPos(mapHeight/2, 0),true);
        newNodes.add(mapHeight/2);
        newNodePos = 0;

        generateNewPaths(camera);
    }

    public void generateNewPaths(Camera camera) {
        // Change this to something random
        int pathWidth = 14;

        while(newNodePos - camera.getPosition().getX()/Game.tileSize + pathWidth < this.mapWidth*(1 - blocksDeleted)) {
            if (camera.getPosition().getX() > map[0][(int)mapWidth*1/2].getPosition().getX()) {
                System.out.println("Deleting oldest block");
                deleteOldestBlock();
            }
            System.out.println("Generating new block");
            int oldNodePos = newNodePos;
            newNodePos = oldNodePos + pathWidth;

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
            if (oldNodes.size() > 1) {
                createPaths(oldNodes, newNodes, oldNodePos, newNodePos);
            }
        }
    }

    private void deleteOldestBlock() {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                map[y][x] = map[y][x + mapWidth];
                map[y][x + mapWidth] = map[y][x + mapWidth*2];
                Vector2D newPos = new Vector2D(map[y][x + mapWidth*2].getPosition().getX() + mapWidth * Game.tileSize, map[y][x + mapWidth*2].getPosition().getY());
                map[y][x + mapWidth*2] = new Tile(newPos);
            }
        }
        blocksDeleted++;
        newNodePos -= mapWidth;
    }

    private void createPaths(ArrayList<Integer> leftNodes, ArrayList<Integer> rightNodes, int leftX, int rightX) {
        for (int i = 0; i < 3; i++) {
            connectNodes(new Vector2D(leftX, leftNodes.get(i)), new Vector2D(rightX, rightNodes.get(i)));
        }
    }

    private void connectNodes(Vector2D leftNode, Vector2D rightNode) {
        SecureRandom rand = new SecureRandom();
        //int noPoints = (int) rand.nextGaussian(3.0f, 1.0f);
        int noPoints = 2;
        LinkedList<Vector2D> pointPosition = new LinkedList<>();
        pointPosition.add(leftNode);

        for (int i = 0; i < noPoints; i++) {
            int pointX = rand.nextInt(leftNode.intX(), rightNode.intX());
            int pointY;
            if (leftNode.intY() != rightNode.intY()) {
                pointY = rand.nextInt(Math.min(leftNode.intY(), rightNode.intY()) + rand.nextInt(-2, 2), Math.max(leftNode.intY(), rightNode.intY()) + rand.nextInt(-2, 2));
            } else {
                pointY = leftNode.intY() + rand.nextInt(-2, 2);
            }
            System.out.println("X: " + pointX + " Y: " + pointY);
            pointPosition.add(new Vector2D(pointX, pointY));
        }
        pointPosition.add(rightNode);

        pointPosition.sort(Comparator.comparing(Vector2D::intX));
        // Declare and initialize variables needed
        int xPos = pointPosition.get(0).intX();
        int yPos = pointPosition.get(0).intY();
        int pointIndex = 1;
        // Loop over and create all horizontal paths
        while(xPos < pointPosition.peekLast().intX()) {
            if (xPos == pointPosition.get(pointIndex).intX()) {
                yPos = pointPosition.get(pointIndex).intY();
                pointIndex++;
            }
            map[yPos][xPos] = new Tile(ContentManager.getSprite("checker"), gridToPos(yPos, xPos));
            xPos++;
        }
        // Loop over and create all vertical paths
        for(pointIndex = 0; pointIndex < pointPosition.size()-1; pointIndex++) {
            xPos = pointPosition.get(pointIndex+1).intX();
            yPos = Math.min(pointPosition.get(pointIndex).intY(), pointPosition.get(pointIndex+1).intY());
            while (yPos < Math.max(pointPosition.get(pointIndex).intY(), pointPosition.get(pointIndex+1).intY())+1) {
                map[yPos][xPos] = new Tile(ContentManager.getSprite("checker"), gridToPos(yPos, xPos));
                yPos++;
            }
        }

    }
    public Tile[][] getMap() {
        return this.map;
    }

}
