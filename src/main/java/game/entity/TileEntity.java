package game.entity;

import core.GamePanel;

import java.awt.Rectangle;

public class TileEntity {
    public int worldX, worldY;
    public final Rectangle hitbox;
    public int hitboxX = 0, hitboxY = 0;
    private final String id;

    public TileEntity(String id) {
        this.id = id;
        hitbox = new Rectangle(0, 0,
                GamePanel.getTileSize(),
                GamePanel.getTileSize());
    }

    public String getId(){
        return id;
    }
}
