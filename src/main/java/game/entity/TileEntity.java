package game.entity;

import core.impl.ScreenSettings;

import java.awt.Rectangle;

public class TileEntity {
    public int worldX, worldY;
    public final Rectangle hitbox;
    public int hitboxX = 0, hitboxY = 0;
    private final String id;
    protected final int TILE_SIZE = ScreenSettings.getTileSize();

    public TileEntity(String id, int x, int y) {
        this.id = id;
        hitbox = new Rectangle(0, 0,
                TILE_SIZE,
                TILE_SIZE);

        this.worldX = x * TILE_SIZE;
        this.worldY = y * TILE_SIZE;
    }

    public String getId() {
        return id;
    }

    public void setHitbox(int x, int y, int width, int height) {
        hitbox.setBounds(x, y, width * ScreenSettings.getScale(),
                height * ScreenSettings.getScale());
        hitboxX = x;
        hitboxY = y;
    }
}
