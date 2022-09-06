package mapgen;

import core.ContentManager;
import entity.GameObject;
import metrics.Vector2D;

import java.awt.*;

public class Tile extends GameObject {
    boolean node;

    public Tile(Image sprite, Vector2D pos) {
        super(sprite, pos);
        this.node = false;
    }

    public Tile(Image sprite, Vector2D pos, boolean node) {
        super(sprite, pos);
        this.node = node;
    }

    public Tile(Vector2D pos) {
        super(ContentManager.getSprite("fire"), pos);
        this.node = node;
    }

    @Override
    public void update() {

    }
}
