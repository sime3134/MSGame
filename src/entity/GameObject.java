package entity;

import metrics.Vector2D;

import java.awt.*;

public abstract class GameObject {
    protected final Image sprite;
    protected Vector2D position;

    public GameObject(Image sprite) {
        this.sprite = sprite;
        position = new Vector2D(0,0);
    }

    public GameObject(Image sprite, Vector2D position) {
        this.sprite = sprite;
        this.position = position;
    }

    public abstract void update();

    public void draw(Graphics graphics) {
        graphics.drawImage(sprite, position.intX(), position.intY(), null);
    }
}
