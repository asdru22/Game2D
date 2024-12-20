package core;

public class PanelSettings {
    private final int ORIGINAL_TILE_SIZE = 16;
    private final int SCALE = 3;
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    private final int MAX_SCREEN_COL = 16;
    private final int MAX_SCREEN_ROW = 12;
    private final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    private final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    private final int TARGET_FPS = 60;

    private final String NAME = "Silly goober";

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getTargetFPS() {
        return TARGET_FPS;
    }

    public String getName(){
        return NAME;
    }

    public int getCol() {
        return MAX_SCREEN_COL;
    }

    public int getRow() {
        return MAX_SCREEN_ROW;
    }

    public int getOriginalTileSize() {
        return ORIGINAL_TILE_SIZE;
    }
}
