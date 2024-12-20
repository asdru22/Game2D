package game.object;

import core.Panel;
import game.entity.Player;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseObject {
    private BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;

    public void setImage(String path) {
        this.image = IOUtils.loadImage(String.format("object/%s", path));
    }

    public void draw(Graphics2D g2d, Panel gamePanel) {
        Player p = gamePanel.getPlayer();
        int tileSize = gamePanel.getPanelSettings().getTileSize();

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

}
