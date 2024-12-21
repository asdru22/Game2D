package game.object;

import core.CorePanel;
import core.PanelSettings;
import game.entity.Player;
import game.entity.TileEntity;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseObject extends TileEntity {
    private BufferedImage image;
    protected boolean collision = false;

    public BaseObject(String name, int x, int y) {
        super(name, x, y);
        setImage(name);
    }

    public void setImage(String path) {
        this.image = IOUtils.loadImage(String.format("object/%s", path));
    }

    public void draw(Graphics2D g2d, CorePanel corePanel) {
        Player p = corePanel.getPlayer();
        int tileSize = PanelSettings.getTileSize();

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
}
