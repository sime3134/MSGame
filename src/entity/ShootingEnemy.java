package entity;

import core.ContentManager;
import core.Game;
import input.Controller;
import metrics.Vector2D;

import java.awt.*;

public class ShootingEnemy extends Enemy{
    double shootingTimer;

    public ShootingEnemy(Controller controller, Image sprite) {
        super(controller, sprite, 25);
        shootingTimer = 0;
    }

    @Override
    public void update(Game game) {
        super.update(game);
        if(shootingTimer >= 50){
            shoot(game);
            shootingTimer = 0;
        }
        shootingTimer++;
    }

    private void shoot(Game game) {
        Player player = game.getPlayer();
        Vector2D direction = player.getPosition().distanceTo(getPosition());
        Projectile p = new Projectile(ContentManager.getSprite("fireProjectile"),
                new Vector2D(position.getX(), position.getY()), direction);
        game.addGameObjectNextUpdate(p);
    }
}
