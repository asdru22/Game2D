package tile;

import io.IOUtils;

import java.awt.image.BufferedImage;

public class Tile {
    private final BufferedImage image;
    private final boolean collision;

    public Tile(String texturePath, boolean collision) {
        this.image = IOUtils.loadImage(String.format("tile/%s",texturePath));
        this.collision = collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean hasCollision() {
        return collision;
    }

}
