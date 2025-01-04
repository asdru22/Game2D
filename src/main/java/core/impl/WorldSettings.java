package core.impl;

public class WorldSettings {
    private static final int
            MAX_WORLD_COL = 50,
            MAX_WORLD_ROW = 50;
    public static int getWorldCol() {
        return MAX_WORLD_COL;
    }

    public static int getWorldRow() {
        return MAX_WORLD_ROW;
    }
}
