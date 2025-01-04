package game.entity;

import core.CorePanel;
import game.stats.Stats;

public abstract class Enemy extends NPC {
    public Enemy(int worldX, int worldY, Stats stats, String id, CorePanel corePanel) {
        super(worldX, worldY, stats, id, corePanel);
    }
}
