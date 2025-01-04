package game.worldevent;

import core.CorePanel;
import core.impl.ScreenSettings;
import game.entity.Direction;
import game.entity.Player;

import java.awt.*;
import java.util.Iterator;

public abstract class Event {

    protected CorePanel corePanel;
    private final Rectangle hitbox;
    private final int hitboxX, hitboxY;
    private final int eventX, eventY;
    private final Direction triggerDirection;

    private int timer = 0;
    private int cooldown = 60;
    private boolean canTrigger = true;
    private boolean singleTrigger = false;

    public Event(int x, int y) {
        this(x, y, null);
    }

    public Event(int x, int y, Direction triggerDirection) {
        final int size = ScreenSettings.getTileSize();
        this.hitbox = new Rectangle();
        this.hitbox.x = 23;
        this.hitbox.y = 23;
        this.hitbox.width = 2;
        this.hitbox.height = 2;
        this.hitboxX = this.hitbox.x;
        this.hitboxY = this.hitbox.y;

        this.eventX = x * size;
        this.eventY = y * size;
        this.triggerDirection = triggerDirection;
    }

    public void checkTrigger(Player player, Iterator<Event> iterator) {

        player.hitbox.x += player.worldX;
        player.hitbox.y += player.worldY;

        hitbox.x += eventX;
        hitbox.y += eventY;

        if ((player.hitbox.intersects(hitbox)) &&
                (player.getDirections().contains(triggerDirection) || triggerDirection == null)) {
            if (canTrigger) {
                event();
                timer = cooldown;
                canTrigger = false;
                if (singleTrigger) {
                    iterator.remove();
                }
            }
            // when the player moved out of the hitbox
        } else if (!canTrigger) {
            canTrigger = true;
        }

        player.hitbox.x = player.hitboxX;
        player.hitbox.y = player.hitboxY;

        hitbox.x = hitboxX;
        hitbox.y = hitboxY;
    }

    public abstract void event();

    public int decreaseCooldown() {
        if (timer > 0) {
            timer--;
        }
        return timer;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void initialize(CorePanel corePanel) {
        this.corePanel = corePanel;
    }

    protected void setSingleTrigger() {
        this.singleTrigger = true;
    }
}
