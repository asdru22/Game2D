package game.entity;

import core.GamePanel;

import java.util.EnumSet;

public abstract class Entity extends TileEntity {
    protected int speed;

    protected final EnumSet<Direction> direction = EnumSet.noneOf(Direction.class);
    protected final EnumSet<Direction> collisions = EnumSet.noneOf(Direction.class);

    private final AnimationHandler animations;

    public Entity(int worldX, int worldY, int speed, String id) {
        super(id);
        this.worldX = worldX * GamePanel.getTileSize();
        this.worldY = worldY * GamePanel.getTileSize();
        this.speed = speed;

        resetCollisions();
        direction.add(Direction.DOWN);

        animations = new AnimationHandler(this);
        animations.add(AnimationHandler.Animations.WalkingUp, "walking/up", 2);
        animations.add(AnimationHandler.Animations.WalkingDown, "walking/down", 2);
        animations.add(AnimationHandler.Animations.WalkingLeft, "walking/left", 2);
        animations.add(AnimationHandler.Animations.WalkingRight, "walking/right", 2);
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
}


