package game.entity;

import core.PanelSettings;

import java.awt.Rectangle;

public class TileEntity {
    public int worldX, worldY;
    public final Rectangle hitbox;
    public int hitboxX = 0, hitboxY = 0;
    private final String id;
    protected final int TILE_SIZE = PanelSettings.getTileSize();

    public TileEntity(String id, int x, int y) {
        this.id = id;
        hitbox = new Rectangle(0, 0,
                PanelSettings.getTileSize(),
                PanelSettings.getTileSize());

        this.worldX = x * TILE_SIZE;
        this.worldY = y * TILE_SIZE;
    }

    public String getId() {
        return id;
    }
}
