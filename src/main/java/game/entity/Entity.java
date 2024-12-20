package game.entity;


import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;

public abstract class Entity {

    protected int worldX;
    protected int worldY;
    protected int speed;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteCounter = 0, spriteNum = 0;
    protected Rectangle hitbox;

    protected final EnumSet<Direction> direction = EnumSet.noneOf(Direction.class);
    protected final EnumSet<Direction> collisions = EnumSet.noneOf(Direction.class);
    public void initialize(String path) {

        resetCollisions();
        direction.add(Direction.DOWN);
        this.up1 = IOUtils.loadImage(String.format("%s/up_1", path));
        this.up2 = IOUtils.loadImage(String.format("%s/up_2", path));
        this.down1 = IOUtils.loadImage(String.format("%s/down_1", path));
        this.down2 = IOUtils.loadImage(String.format("%s/down_2", path));
        this.left1 = IOUtils.loadImage(String.format("%s/left_1", path));
        this.left2 = IOUtils.loadImage(String.format("%s/left_2", path));
        this.right1 = IOUtils.loadImage(String.format("%s/right_1", path));
        this.right2 = IOUtils.loadImage(String.format("%s/right_2", path));
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
}


