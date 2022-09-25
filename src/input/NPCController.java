package input;

public class NPCController implements Controller{
    @Override
    public boolean requestedUp() {
        return false;
    }

    @Override
    public boolean requestedDown() {
        return false;
    }

    @Override
    public boolean requestedLeft() {
        return false;
    }

    @Override
    public boolean requestedRight() {
        return false;
    }
}
