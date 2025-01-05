package game.entity;

import core.CorePanel;
import core.impl.ScreenSettings;
import game.stat.stats.Damage;
import game.stat.stats.Health;
import game.stat.stats.Speed;
import io.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity implements Collidable {

    private final KeyHandler keyHandler;
    private final int screenX, screenY;

    public Player(CorePanel corePanel, KeyHandler keyHandler) {
        super(23, 21, "player", corePanel);
        this.keyHandler = keyHandler;

        int tileSize = ScreenSettings.getTileSize();
        screenX = ScreenSettings.getScreenWidth() / 2 - tileSize / 2;
        screenY = ScreenSettings.getScreenHeight() / 2 - tileSize / 2;

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

        drawImage(g2d, this.getAnimations().getCurrentAnimation().getCurrentFrame(),
                screenX, screenY, ScreenSettings.getTileSize());
    }

    @Override
    public void addAnimations() {
        animations.add(AnimationHandler.Animations.WalkingUp, "walking/up", 2);
        animations.add(AnimationHandler.Animations.WalkingDown, "walking/down", 2);
        animations.add(AnimationHandler.Animations.WalkingLeft, "walking/left", 2);
        animations.add(AnimationHandler.Animations.WalkingRight, "walking/right", 2);
    }

    @Override
    public void setStats() {
        stats.addStat(new Damage(1));
        stats.addStat(new Speed(4));
        stats.addStat(new Health(6));
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    @Override
    public void onCollision(Entity entity) {
        // triggers for enemy to player and player to enemy collision, but not npc to player collision
        if (entity instanceof Collidable && entity instanceof Enemy) {
            ((Collidable) entity).onCollision(this);
        }
    }
}
