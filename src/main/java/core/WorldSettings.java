package core;

public class WorldSettings {
    private static final int MAX_WORLD_COL = 50,
            MAX_WORLD_ROW = 50,
            WORLD_WIDTH = PanelSettings.getTileSize() * MAX_WORLD_COL,
            WORLD_HEIGHT = PanelSettings.getTileSize() * MAX_WORLD_ROW;

    public static int getWorldWidth() {
        return WORLD_WIDTH;
    }

    public static int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    public static int getWorldCol() {
        return MAX_WORLD_COL;
    }

    public static int getWorldRow() {
        return MAX_WORLD_ROW;
    }
}
