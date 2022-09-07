package gfx;

import metrics.Vector2D;

public class Camera {
    private Vector2D position;

    private double moveSpeed;

    public Camera(){
        position = new Vector2D(0,0);
        moveSpeed = 0.1;
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
}
