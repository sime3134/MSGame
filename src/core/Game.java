package core;

import gfx.GameFrame;

import java.awt.*;

public class Game {

    private final GameFrame gameFrame;

    public Game() {
        gameFrame = new GameFrame(this);
    }

    public GameFrame getGameFrame(){
        return gameFrame;
    }

    public void draw(Graphics graphics) {
    }

    public void update() {
    }
}
