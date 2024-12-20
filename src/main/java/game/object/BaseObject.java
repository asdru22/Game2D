package game.object;

import core.GamePanel;
import core.PanelSettings;
import game.entity.Player;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseObject {
    private BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    private final Rectangle hitbox;

    public BaseObject() {
        hitbox = new Rectangle(0, 0,
                16 * GamePanel.getTileSize(), 16 * GamePanel.getTileSize());
    }

    public void setImage(String path) {
        this.image = IOUtils.loadImage(String.format("object/%s", path));
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        Player p = gamePanel.getPlayer();
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

}
