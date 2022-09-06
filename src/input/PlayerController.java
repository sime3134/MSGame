package input;

import java.awt.event.KeyEvent;

public class PlayerController implements Controller{

    private static PlayerController playerController;

    private final Input input = Input.getInstance();

    public static PlayerController getInstance() {
        if(playerController == null){
            playerController = new PlayerController();
        }
        return playerController;
    }

    private PlayerController(){
    }

    @Override
    public boolean requestedUp() {
        return input.isKeyCurrentlyPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean requestedDown() {
        return input.isKeyCurrentlyPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean requestedLeft() {
        return input.isKeyCurrentlyPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean requestedRight() {
        return input.isKeyCurrentlyPressed(KeyEvent.VK_D);
    }
}
