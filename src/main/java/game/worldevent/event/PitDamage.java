package game.worldevent.event;

import game.worldevent.Event;

public class PitDamage extends Event {

    public PitDamage() {
        super(27, 16);
        this.setCooldown(60);
    }

    @Override
    public void event() {
        corePanel.setDialogueState(new String[]{"You fall into a pit stupid"});
        corePanel.getPlayer().getStats().health--;
    }
}
