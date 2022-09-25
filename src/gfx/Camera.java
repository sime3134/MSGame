package gfx;

import entity.GameObject;
import entity.Projectile;
import metrics.Vector2D;

public class Camera {
    private Vector2D position;

    private double moveSpeed;

    public Camera(){
        position = new Vector2D(0,0);
        moveSpeed = 0;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void update() {
        position.add(new Vector2D(moveSpeed, 0));
    }

    public boolean isOutsideBounds(GameObject obj) {
        return obj.getPosition().getX() < getPosition().getX() || obj.getPosition().getX() > getPosition().getX() + GameWindow.SCREEN_WIDTH
        || obj.getPosition().getY() < getPosition().getY() || obj.getPosition().getY() > getPosition().getY() + GameWindow.SCREEN_HEIGHT;
    }
}
