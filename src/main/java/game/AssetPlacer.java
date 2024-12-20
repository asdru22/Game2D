package game;

import core.GamePanel;
import core.PanelSettings;
import core.WorldSettings;
import game.object.Chest;
import game.object.Door;
import game.object.Key;

public class AssetPlacer {
    private final GamePanel gamePanel;

    public AssetPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        final int tileSize = PanelSettings.getTileSize();

        gamePanel.objs.add(new Key(23 * tileSize, 7 * tileSize));
        gamePanel.objs.add(new Key(23 * tileSize, 40 * tileSize));
        gamePanel.objs.add(new Key(37 * tileSize, 7 * tileSize));

        gamePanel.objs.add(new Door(10 * tileSize, 11 * tileSize));
        gamePanel.objs.add(new Door(8 * tileSize, 28 * tileSize));
        gamePanel.objs.add(new Door(12 * tileSize, 22 * tileSize));

        gamePanel.objs.add(new Chest(10 * tileSize, 7 * tileSize));
    }
}
