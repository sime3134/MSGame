package core;

import entity.GameObject;
import entity.Player;
import gfx.Camera;
import input.PlayerController;
import mapgen.Map;
import mapgen.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private ContentManager content;

    private Camera camera;
    private Player player;
    private Map map;

    private double screenMoveSpeed = 1;

    public static int tileSize = 24;
    private List<GameObject> gameObjects;

    public Game() {
        content = new ContentManager();
        camera = new Camera();
        player = new Player(PlayerController.getInstance(), content.getSprite("player"));
        gameObjects = new ArrayList<>();
        map = new Map(30, 15, camera);
        gameObjects.add(player);
    }

    public void draw(Graphics graphics) {
        for (Tile[] row : map.getMap()) {
            for (Tile tile : row) {
                if (tile != null) {
                    tile.draw(graphics, camera);
                }
            }
        }
        for(GameObject obj : gameObjects){
            obj.draw(graphics, camera);
        }
    }

    public void update() {
        for(GameObject obj : gameObjects){
            obj.update();
        }
        camera.update();
        map.generateNewPaths(camera);
    }
}
