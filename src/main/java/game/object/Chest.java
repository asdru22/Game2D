package game.object;

public class Chest extends BaseObject {
    public Chest(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "Chest";
        setImage("chest");
    }
}
