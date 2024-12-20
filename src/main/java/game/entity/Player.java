package game.entity;

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
        super(23, 21, 4, "player");
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        int tileSize = gamePanel.getPanelSettings().getTileSize();
        screenX = gamePanel.getPanelSettings().getScreenWidth() / 2 - tileSize / 2;
        screenY = gamePanel.getPanelSettings().getScreenHeight() / 2 - tileSize / 2;

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

        this.getAnimations().update();

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

        if (direction.contains(Direction.UP)) this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingUp);
        if (direction.contains(Direction.DOWN))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingDown);
        if (direction.contains(Direction.LEFT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingLeft);
        if (direction.contains(Direction.RIGHT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingRight);

        BufferedImage image = this.getAnimations().getCurrentAnimation().getCurrentFrame();
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
