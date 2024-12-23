package game.object;

import core.CorePanel;

public class Door extends BaseObject {
    public Door(int x, int y) {
        super("door", x, y);
        collision = true;
    }

    @Override
    public void onCollision(CorePanel corePanel) {

    }
}
