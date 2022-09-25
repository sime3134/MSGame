package core;

import entity.*;
import gfx.Camera;
import input.NPCController;
import input.PlayerController;
import mapgen.Map;
import mapgen.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Camera camera;
    private final Player player;
    private Map map;

    public static int tileSize = 24;
    private final List<GameObject> gameObjects;

    private final List<GameObject> gameObjectsToAddNextUpdate;

    private final List<GameObject> gameObjectsToRemoveNextUpdate;

    public Game() {
        ContentManager.loadContent();
        camera = new Camera();
        player = new Player(PlayerController.getInstance(), ContentManager.getSprite("player"));
        gameObjects = new ArrayList<>();
        gameObjectsToAddNextUpdate = new ArrayList<>();
        gameObjectsToRemoveNextUpdate = new ArrayList<>();
        map = new Map(30, 15, camera);
        addGameObjectNow(player);
        spawnEnemyTest();
    }

    private void spawnEnemyTest() {
        Enemy enemy = new ShootingEnemy(new NPCController(), ContentManager.getSprite("goblin"));
        addGameObjectNow(enemy);
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
            obj.update(this);
        }
        for(GameObject obj: gameObjectsToRemoveNextUpdate){
            removeGameObjectNow(obj);
        }
        for(GameObject obj : gameObjectsToAddNextUpdate){
            addGameObjectNow(obj);
        }
        gameObjectsToAddNextUpdate.clear();
        gameObjectsToRemoveNextUpdate.clear();
        camera.update();
        map.generateNewPaths(camera);
        System.out.println(gameObjects.size());
    }

    private void removeGameObjectNow(GameObject obj) {
        gameObjects.remove(obj);
    }

    public void addGameObjectNow(GameObject obj) {
        gameObjects.add(obj);
    }

    public void addGameObjectNextUpdate(GameObject obj) {
        gameObjectsToAddNextUpdate.add(obj);
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public void removeGameObjectNextUpdate(GameObject obj) {
        gameObjectsToRemoveNextUpdate.add(obj);
    }
}
