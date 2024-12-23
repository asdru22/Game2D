package game.entity;

import core.CorePanel;
import core.PanelSettings;
import math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;

public abstract class Entity extends TileEntity implements Drawable {
    protected int speed;

    protected final EnumSet<Direction> direction = EnumSet.noneOf(Direction.class);
    protected final EnumSet<Direction> collisions = EnumSet.noneOf(Direction.class);

    private final CorePanel corePanel;
    private final AnimationHandler animations;

    public Entity(int worldX, int worldY, int speed, String id, CorePanel corePanel) {
        super(id, worldX, worldY);
        this.corePanel = corePanel;
        this.speed = speed;

        resetCollisions();
        direction.add(Direction.DOWN);

        animations = new AnimationHandler(this);
        animations.add(AnimationHandler.Animations.WalkingUp, "walking/up", 2);
        animations.add(AnimationHandler.Animations.WalkingDown, "walking/down", 2);
        animations.add(AnimationHandler.Animations.WalkingLeft, "walking/left", 2);
        animations.add(AnimationHandler.Animations.WalkingRight, "walking/right", 2);
        animations.initialize();
    }

    public int getWorldY() {
        return worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public boolean isNotColliding(Direction direction) {
        return !collisions.contains(direction);
    }

    public void setColliding(Direction direction) {
        collisions.add(direction);
    }

    public void resetCollisions() {
        collisions.clear();
    }

    public AnimationHandler getAnimations() {
        return animations;
    }

    public void addSpeed(int i) {
        this.speed += i;
    }

    public CorePanel getCorePanel() {
        return corePanel;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Player p = corePanel.getPlayer();
        final int tileSize = PanelSettings.getTileSize();

        int screenX = worldX - p.getWorldX() + p.getScreenX();
        int screenY = worldY - p.getWorldY() + p.getScreenY();
        if (worldX + tileSize > p.getWorldX() - p.getScreenX() &&
                worldX - tileSize < p.getWorldX() + p.getScreenX() &&
                worldY + tileSize > p.getWorldY() - p.getScreenY() &&
                worldY - tileSize < p.getWorldY() + p.getScreenY()
        ) {

            updateAnimation();

            BufferedImage image = this.getAnimations().getCurrentAnimation().getCurrentFrame();
            g2d.drawImage(image, screenX, screenY,
                    tileSize, tileSize, null);
        }
    }

    public void move() {
        // check tile collision
        this.resetCollisions();
        CollisionChecker cc = this.getCorePanel().getCollisionChecker();
        cc.checkTile(this);
        cc.checkEntity(this, this.getCorePanel().getGameObjects().getEntities());
        cc.checkObject(this);

        Vector2f s = new Vector2f();

        if (this.isNotColliding(Direction.UP) && direction.contains(Direction.UP)) {
            s.y -= 1;
        }
        if (this.isNotColliding(Direction.DOWN) && direction.contains(Direction.DOWN)) {
            s.y += 1;
        }
        if (this.isNotColliding(Direction.LEFT) && direction.contains(Direction.LEFT)) {
            s.x -= 1;
        }
        if (this.isNotColliding(Direction.RIGHT) && direction.contains(Direction.RIGHT)) {
            s.x += 1;
        }

        s.normalize();
        s.multiply(speed);
        if (s.x != 0 || s.y != 0) {
            this.getAnimations().update();
            this.worldX += (int) s.x;
            this.worldY += (int) s.y;
        }
    }

    public abstract void update();

    public void updateAnimation() {
        if (direction.contains(Direction.UP)) this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingUp);
        if (direction.contains(Direction.DOWN))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingDown);
        if (direction.contains(Direction.LEFT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingLeft);
        if (direction.contains(Direction.RIGHT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingRight);
    }

    public void onCollision() {
    }
}


