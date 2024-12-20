package game.object;

public class Door extends BaseObject {
    public Door(int x, int y) {
        super("door");
        this.worldX = x;
        this.worldY = y;
        collision = true;
    }
}
