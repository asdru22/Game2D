package game.tile;

public class TileFactory {
    public static final Tile GRASS = new Tile("grass",false);
    public static final Tile WALL = new Tile("wall",true);
    public static final Tile WATER = new Tile("water",false);
    public static final Tile EARTH = new Tile("earth",false);
    public static final Tile TREE = new Tile("tree",true);
    public static final Tile SAND = new Tile("sand",false);
}
