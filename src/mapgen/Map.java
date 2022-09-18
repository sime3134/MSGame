package mapgen;

import core.ContentManager;
import core.Game;
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
            if (oldNodes.size() > 1) {
                createPaths(oldNodes, newNodes, oldNodePos, newNodePos);
            }
        }
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
