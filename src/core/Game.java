package core;

import entity.GameObject;
import entity.Player;
import input.PlayerController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final ContentManager content;
    private final Player player;

    private final List<GameObject> gameObjects;

    public Game() {
        content = new ContentManager();
        player = new Player(PlayerController.getInstance(), content.getSprite("player"));
        gameObjects = new ArrayList<>();
        gameObjects.add(player);
    }

    public void draw(Graphics graphics) {
        for(GameObject obj : gameObjects){
            obj.draw(graphics);
        }
    }

    public void update() {
        for(GameObject obj : gameObjects){
            obj.update();
        }
    }
}
