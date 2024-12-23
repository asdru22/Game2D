package game.tile;

import core.CorePanel;
import core.PanelSettings;
import core.WorldSettings;
import game.entity.Drawable;
import game.entity.Player;
import io.IOUtils;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileManager implements Drawable {
    private final CorePanel corePanel;
    private final Map<Integer, Tile> tiles;
    private final int[][] tileMap;

    public TileManager(CorePanel corePanel) {
        this.corePanel = corePanel;
        tiles = new HashMap<>();

        tiles.put(10, new Tile("grass00", false));
        tiles.put(11, new Tile("grass01", false));
        tiles.put(12, new Tile("water00", true));
        tiles.put(13, new Tile("water01", true));
        tiles.put(14, new Tile("water02", true));
        tiles.put(15, new Tile("water03", true));
        tiles.put(16, new Tile("water04", true));
        tiles.put(17, new Tile("water05", true));
        tiles.put(18, new Tile("water06", true));
        tiles.put(19, new Tile("water07", true));
        tiles.put(20, new Tile("water08", true));
        tiles.put(21, new Tile("water09", true));
        tiles.put(22, new Tile("water10", true));
        tiles.put(23, new Tile("water11", true));
        tiles.put(24, new Tile("water12", true));
        tiles.put(25, new Tile("water13", true));
        tiles.put(26, new Tile("road00", false));
        tiles.put(27, new Tile("road01", false));
        tiles.put(28, new Tile("road02", false));
        tiles.put(29, new Tile("road03", false));
        tiles.put(30, new Tile("road04", false));
        tiles.put(31, new Tile("road05", false));
        tiles.put(32, new Tile("road06", false));
        tiles.put(33, new Tile("road07", false));
        tiles.put(34, new Tile("road08", false));
        tiles.put(35, new Tile("road09", false));
        tiles.put(36, new Tile("road10", false));
        tiles.put(37, new Tile("road11", false));
        tiles.put(38, new Tile("road12", false));
        tiles.put(39, new Tile("earth", false));
        tiles.put(40, new Tile("wall", false));
        tiles.put(41, new Tile("tree", true));


        try {
            tileMap = IOUtils.readMap("world1", WorldSettings.getWorldCol(),
                    WorldSettings.getWorldRow());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    public Map<Integer, Tile> getTiles() {
        return tiles;
    }
}
