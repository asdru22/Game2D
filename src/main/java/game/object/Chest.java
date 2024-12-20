package game.object;

public class Door extends BaseObject {
    public Door(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "Door";
        setImage("door");
    }
}
