package game.object;

import core.CorePanel;
import core.impl.ScreenSettings;
import game.entity.Player;
import game.entity.TileEntity;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseObject extends TileEntity {
    private final BufferedImage image;
    protected boolean collision = false;

    public BaseObject(String name, int x, int y) {
        super(name, x, y);
        this.image = IOUtils.loadScaledImage(String.format("object/%s", name));
    }

    public void draw(Graphics2D g2d, CorePanel corePanel) {
        Player p = corePanel.getPlayer();
        int tileSize = ScreenSettings.getTileSize();

        int screenX = worldX - p.getWorldX() + p.getScreenX();
        int screenY = worldY - p.getWorldY() + p.getScreenY();
        if (worldX + tileSize > p.getWorldX() - p.getScreenX() &&
                worldX - tileSize < p.getWorldX() + p.getScreenX() &&
                worldY + tileSize > p.getWorldY() - p.getScreenY() &&
                worldY - tileSize < p.getWorldY() + p.getScreenY()
        ) {
            g2d.drawImage(image, screenX, screenY,
                    tileSize, tileSize, null);
        }
    }

    public boolean isCollideable() {
        return collision;
    }

    public abstract void onCollision(CorePanel corePanel);
}
