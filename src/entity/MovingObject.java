package entity;

import core.Game;
import input.Controller;
import metrics.Vector2D;

import java.awt.*;

public class MovingObject extends GameObject{
    private final Controller controller;
    protected Vector2D velocity;
    protected double speed;
    public MovingObject(Controller controller, Image sprite) {
        super(sprite);
        this.controller = controller;
        velocity = new Vector2D(0,0);
        speed = 4;
    }

    public void update(Game game){
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

        velocity.setX(velocityX);
        velocity.setY(velocityY);

        velocity.normalize();
        velocity.multiply(speed);
        position.add(velocity);
    }
}
