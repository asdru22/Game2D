package core;

public class ScreenSettings {
    private final int ORIGINAL_TILE_SIZE = 16,
            SCALE = 3,
            TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE,
            MAX_SCREEN_COL = 16,
            MAX_SCREEN_ROW = 12,
            SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL,
            SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
}
