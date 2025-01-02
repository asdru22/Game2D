package core;

public class ScreenSettings {
    private static final int
            ORIGINAL_TILE_SIZE = 16,
            SCALE = 3,
            TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE,
            MAX_SCREEN_COL = 16,
            MAX_SCREEN_ROW = 12,
            SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL,
            SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW,
            TARGET_FPS = 60;

    private static final String NAME = "Silly goober";

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static String getName() {
        return NAME;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getTargetFPS() {
        return TARGET_FPS;
    }

}
