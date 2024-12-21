package game.tile;

import core.CorePanel;
import core.PanelSettings;
import core.WorldSettings;
import game.entity.Player;
import io.IOUtils;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileManager {
    private final CorePanel corePanel;
    private final Map<Integer, Tile> tiles;
    private final int[][] tileMap;

    public TileManager(CorePanel corePanel) {
        this.corePanel = corePanel;
        tiles = new HashMap<>();

        tiles.put(0, TileFactory.GRASS);
        tiles.put(1, TileFactory.WALL);
        tiles.put(2, TileFactory.WATER);
        tiles.put(3, TileFactory.EARTH);
        tiles.put(4, TileFactory.TREE);
        tiles.put(5, TileFactory.SAND);

        try {
            tileMap = IOUtils.readMap("world1", WorldSettings.getWorldCol(),
                    WorldSettings.getWorldRow());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2d) {
        final int tileSize = PanelSettings.getTileSize();
        int maxRow = tileMap.length; // Map height
        int maxCol = tileMap[0].length; // Map width
        Player p = corePanel.getPlayer();

        for (int worldCol = 0; worldCol < maxCol; worldCol++) {
            for (int worldRow = 0; worldRow < maxRow; worldRow++) {
                int tileNum = tileMap[worldCol][worldRow];

                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - p.getWorldX() + p.getScreenX();
                int screenY = worldY - p.getWorldY() + p.getScreenY();
                if (worldX + tileSize > p.getWorldX() - p.getScreenX() &&
                        worldX - tileSize < p.getWorldX() + p.getScreenX() &&
                        worldY + tileSize > p.getWorldY() - p.getScreenY() &&
                        worldY - tileSize < p.getWorldY() + p.getScreenY()
                ) {
                    g2d.drawImage(tiles.get(tileNum).getImage(), screenX, screenY,
                            tileSize, tileSize, null);
                }
            }
        }
    }

    public int getTileNum(int x, int y) {
        return tileMap[x][y];
    }

    public Map<Integer, Tile> getTiles(){
        return tiles;
    }
}
