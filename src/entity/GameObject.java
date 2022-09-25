package entity;

import core.Game;
import gfx.Camera;
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

    public abstract void update(Game game);

    public void draw(Graphics graphics, Camera camera) {
        graphics.drawImage(sprite, getRenderPosition(camera).intX(), getRenderPosition(camera).intY(), null);
    }

    public Vector2D getRenderPosition(Camera camera){
        return new Vector2D(
                getPosition().getX() - camera.getPosition().getX(),
                getPosition().getY() - camera.getPosition().getY());
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
