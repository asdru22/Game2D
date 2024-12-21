package game.object;

public class Door extends BaseObject {
    public Door(int x, int y) {
        super("door", x, y);
        collision = true;
    }
}
