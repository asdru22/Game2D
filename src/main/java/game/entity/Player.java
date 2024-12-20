package entity;

import core.Panel;
import io.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final Panel gamePanel;
    private final KeyHandler keyHandler;
    private final int screenX, screenY;

    public Player(Panel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        int tileSize = gamePanel.getPanelSettings().getTileSize();

        screenX = gamePanel.getPanelSettings().getScreenWidth() / 2 - tileSize / 2;
        screenY = gamePanel.getPanelSettings().getScreenHeight() / 2 - tileSize / 2;

        worldX = tileSize * 23;
        worldY = tileSize * 21;
        speed = 4;
        this.initialize("entity/player/walking");

        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
    }

    public void update() {

        if (keyHandler.isWASDPressed()) {
            walk();
        }
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

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            spriteNum = (spriteNum == 0) ? 1 : 0;
        }


        this.resetCollisions();
        gamePanel.getCollisionChecker().checkTile(this);

        if (this.isNotColliding(Direction.UP) && direction.contains(Direction.UP)) {
            this.worldY -= speed;
        }
        if (this.isNotColliding(Direction.DOWN) && direction.contains(Direction.DOWN)) {
            this.worldY += speed;
        }
        if (this.isNotColliding(Direction.LEFT) && direction.contains(Direction.LEFT)) {
            this.worldX -= speed;
        }

        if (this.isNotColliding(Direction.RIGHT) && direction.contains(Direction.RIGHT)) {
            this.worldX += speed;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);

        BufferedImage image = null;

        if (direction.contains(Direction.UP)) image = (spriteNum == 0) ? up1 : up2;
        if (direction.contains(Direction.DOWN)) image = (spriteNum == 0) ? down1 : down2;
        if (direction.contains(Direction.LEFT)) image = (spriteNum == 0) ? left1 : left2;
        if (direction.contains(Direction.RIGHT)) image = (spriteNum == 0) ? right1 : right2;

        int tileSize = this.gamePanel.getPanelSettings().getTileSize();
        g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
