package entity;

import input.Controller;

import java.awt.*;

public abstract class Enemy extends MovingObject {
    double hp;

    protected Enemy(Controller controller, Image sprite, double hp) {
        super(controller, sprite);
        this.hp = hp;
    }
}
