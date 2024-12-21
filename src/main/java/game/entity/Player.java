package game.entity;

import core.CorePanel;
import core.PanelSettings;
import io.Sound;
import io.SoundType;
import game.object.BaseObject;
import io.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final CorePanel corePanel;
    private final KeyHandler keyHandler;
    private final int screenX, screenY;
    private int keys = 0;

    public Player(CorePanel corePanel, KeyHandler keyHandler) {
        super(23, 21, 4, "player");
        this.corePanel = corePanel;
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

        // check tile collision
        this.resetCollisions();
        CollisionChecker cc = corePanel.getCollisionChecker();
        cc.checkTile(this);

        // check object collision
        BaseObject collidedWith = cc.checkObject(this);
        objectCollision(collidedWith);

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
        int tileSize = PanelSettings.getTileSize();
        g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private void objectCollision(BaseObject obj) {
        if (obj == null) return;

        switch (obj.getId()) {
            case "key" -> {
                Sound.play(SoundType.COIN);
                keys++;
                corePanel.getGameObjects().getObjects().remove(obj);
            }
            case "door" -> {
                if (keys >= 1) {
                    Sound.play(SoundType.UNLOCK);
                    corePanel.getGameObjects().getObjects().remove(obj);
                    keys--;
                }
            }
            case "boots" -> {
                Sound.play(SoundType.POWERUP);
                speed += 2;
                corePanel.getGameObjects().getObjects().remove(obj);
            }
        }

    }


}
