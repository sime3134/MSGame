package entity;

import input.Controller;
import metrics.Vector2D;

import java.awt.*;

public class MovingObject extends GameObject{
    private Controller controller;
    protected Vector2D velocity;
    protected double speed;
    public MovingObject(Controller controller, Image sprite) {
        super(sprite);
        this.controller = controller;
    }

    public void update(){
        int velocityX = 0;
        int velocityY = 0;

        if (controller.requestedUp())
            velocityY--;
        if (controller.requestedDown())
            velocityY++;
        if (controller.requestedLeft())
            velocityX--;
        if (controller.requestedRight())
            velocityX++;

        velocity = new Vector2D(velocityX, velocityY);

        velocity.normalize();
        velocity.multiply(speed);
        position.add(velocity);
    }
}
