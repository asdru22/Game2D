package game.entity;

import core.CorePanel;

public abstract class NPC extends Entity {

    private int actionCooldown = 0, actionInterval = 60;

    public NPC(int worldX, int worldY, String id, CorePanel corePanel) {
        super(worldX, worldY, id, corePanel);
    }

    @Override
    public void update() {
        move();

        if (actionCooldown > 0) {
            actionCooldown--;
        } else if (actionCooldown == 0) {
            loop();
            actionCooldown = actionInterval;
        }

    }

    public abstract void loop();

    public void setActionInterval(int actionInterval) {
        this.actionInterval = actionInterval;
    }
}
