package game.worldevent.event;

import game.entity.Direction;
import game.worldevent.Event;

public class HealingPool extends Event {

    public HealingPool() {
        super(23, 12, Direction.UP);
        this.setCooldown(600);
        this.setSingleTrigger();
    }

    @Override
    public void event() {
        corePanel.setDialogueState(new String[]{"You drink the water, your health is restored"});
        corePanel.getPlayer().getStats().fullHeal();
    }
}
