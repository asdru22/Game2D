package ui;

import core.impl.ScreenSettings;
import game.entity.Player;
import game.stat.Stats;
import game.stat.stats.Health;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Healthbar {
    private final BufferedImage full, half, empty;

    public Healthbar() {
        full = IOUtils.loadScaledImage("ui/heart_full");
        half = IOUtils.loadScaledImage("ui/heart_half");
        empty = IOUtils.loadScaledImage("ui/heart_empty");
    }

    public void draw(Graphics2D g2d, Player player) {
        Health h = player.getStats().getStat(Health.class);
        int hp = h.getAmount();
        int maxHp = h.getMax();

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
