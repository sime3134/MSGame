package input;

import java.awt.event.*;

/**
 * @author Simon Jern
 * Handles input from mouse and keyboard.
 */
public class Input implements KeyListener{

    private static Input input;

    private final boolean[] currentlyPressed;
    private final boolean[] pressed;

    public static Input getInstance() {
        if(input == null){
            input = new Input();
        }
        return input;
    }

    private Input() {
        currentlyPressed = new boolean[1000];
        pressed = new boolean[1000];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;
    }

    public boolean isKeyCurrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }
}
