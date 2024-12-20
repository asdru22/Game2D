package game.object;

public class Key extends BaseObject {
    public Key(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "Key";
        setImage("key");
    }
}
