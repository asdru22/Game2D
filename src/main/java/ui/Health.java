package ui;

import core.impl.ScreenSettings;
import game.entity.Player;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health {
    private final BufferedImage full, half, empty;

    public Health() {
        full = IOUtils.loadScaledImage("ui/heart_full");
        half = IOUtils.loadScaledImage("ui/heart_half");
        empty = IOUtils.loadScaledImage("ui/heart_empty");
    }

    public void draw(Graphics2D g2d, Player player) {
        int hp = player.getStats().getHealth();
        int maxHp = player.getStats().getMaxHealth();

        final int size = ScreenSettings.getTileSize();

        int x = size / 2;
        int y = size / 2;

        int nFull = hp / 2;
        int nHalf = hp % 2;
        int drawn = 0;

        if (nFull > 0) {
            for (int i = 0; i < nFull; i++) {
                g2d.drawImage(full, x, y, size, size, null);
                x += size;
                drawn++;
            }
        }

        if (nHalf == 1) {
            g2d.drawImage(half, x, y, size, size, null);
            x += size;
            drawn++;
        }

        int remaining = (maxHp + 1) / 2 - drawn;

        for (int i = 0; i < remaining; i++) {
            g2d.drawImage(empty, x, y, size, size, null);
            x += size;
        }
    }
}
