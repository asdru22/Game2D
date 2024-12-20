package core;

public class WorldSettings {
    private final int MAX_WORLD_COL = 50, MAX_WORLD_ROW = 50,
            WORLD_WIDTH, WORLD_HEIGHT;

    public WorldSettings(int tileSize) {
        WORLD_WIDTH = tileSize * MAX_WORLD_COL;
        WORLD_HEIGHT = tileSize * MAX_WORLD_ROW;

    }

    public int getWorldWidth() {
        return WORLD_WIDTH;
    }
    public int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    public int getWorldCol() {
        return MAX_WORLD_COL;
    }
    public int getWorldRow() {
        return MAX_WORLD_ROW;
    }
}
