package entity;

import core.Game;
import metrics.Vector2D;

import java.awt.*;

public class Projectile extends GameObject{
    private Vector2D velocity;
    double speed;

    public Projectile(Image sprite, Vector2D position, Vector2D direction) {
        super(sprite, position);
        speed = 4;
        calculateVelocity(direction);
    }

    private void calculateVelocity(Vector2D direction) {
        velocity = new Vector2D(direction.getX(), direction.getY());
        velocity.normalize();
        velocity.multiply(speed);
    }

    @Override
    public void update(Game game) {
        position.add(velocity);
        if(game.getCamera().isOutsideBounds(this)){
            game.removeGameObjectNextUpdate(this);
        }
    }
}
