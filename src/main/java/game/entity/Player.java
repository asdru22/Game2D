package game.entity;

import core.CorePanel;
import core.PanelSettings;
import io.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final KeyHandler keyHandler;
    private final int screenX, screenY;

    public Player(CorePanel corePanel, KeyHandler keyHandler) {
        super(23, 21, 4, "player", corePanel);
        this.keyHandler = keyHandler;

        int tileSize = PanelSettings.getTileSize();
        screenX = PanelSettings.getScreenWidth() / 2 - tileSize / 2;
        screenY = PanelSettings.getScreenHeight() / 2 - tileSize / 2;

        hitbox.x = 8;
        hitbox.y = 16;
        hitboxX = hitbox.x;
        hitboxY = hitbox.y;
        hitbox.width = 32;
        hitbox.height = 32;
    }

    @Override
    public void update() {
        if (!keyHandler.isWASDPressed()) return;

        walk();
        move();
    }

    private void walk() {
        direction.clear();

        if (keyHandler.isKeyPressed(KeyEvent.VK_W)) {
            this.direction.add(Direction.UP);
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_S)) {
            this.direction.add(Direction.DOWN);
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_A)) {
            this.direction.add(Direction.LEFT);
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_D)) {
            this.direction.add(Direction.RIGHT);
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);

        updateAnimation();

        BufferedImage image = this.getAnimations().getCurrentAnimation().getCurrentFrame();
        int tileSize = PanelSettings.getTileSize();
        g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

}
