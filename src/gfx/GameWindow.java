package gfx;

import core.Game;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

/**
 * @author Simon Jern
 * The window frame that the game is displayed in.
 */
public class GameWindow extends JFrame {
    public static int SCREEN_WIDTH = 720;
    public static int SCREEN_HEIGHT = 480;
    private final Game game;
    private final Canvas canvas;

    public GameWindow(Game game) {
        this.game = game;
        setTitle("PuzzleQuest 2.0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        addListener();

        canvas = new Canvas();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        canvas.setPreferredSize(getContentPane().getPreferredSize());
        canvas.setFocusable(false);
        //canvas.addMouseListener(Input.getInstance());
        //canvas.addMouseMotionListener(Input.getInstance());
        add(canvas);
        addKeyListener(Input.getInstance());
        pack();

        canvas.createBufferStrategy(2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setPreferredSize(e.getComponent().getSize());
                pack();
                SCREEN_WIDTH = getContentPane().getWidth();
                SCREEN_HEIGHT = getContentPane().getHeight();
                canvas.setPreferredSize(getContentPane().getPreferredSize());
            }
        });
    }

    public void draw(){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        game.draw(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
