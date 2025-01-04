package game.entity;

import core.CorePanel;

public abstract class Enemy extends NPC {
    public Enemy(int worldX, int worldY, String id, CorePanel corePanel) {
        super(worldX, worldY, id, corePanel);
    }
}
