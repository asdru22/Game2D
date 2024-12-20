package game.entity;

import core.Panel;

import java.awt.*;
import java.util.EnumSet;

public abstract class Entity {

    protected int worldX;
    protected int worldY;
    protected int speed;
    protected Rectangle hitbox;
    protected final String id;

    protected final EnumSet<Direction> direction = EnumSet.noneOf(Direction.class);
    protected final EnumSet<Direction> collisions = EnumSet.noneOf(Direction.class);

    private AnimationHandler animations;

    public Entity(int worldX, int worldY, int speed, String id) {
        this.worldX = worldX * Panel.getTileSize();
        this.worldY = worldY * Panel.getTileSize();
        this.speed = speed;
        this.id = id;

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

    public String getId() {
        return this.id;
    }

    public AnimationHandler getAnimations() {
        return animations;
    }
}


