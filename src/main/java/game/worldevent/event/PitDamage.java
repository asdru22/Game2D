package game.worldevent.event;

import game.stat.Stats;
import game.stat.stats.Damage;
import game.stat.stats.Health;
import game.worldevent.Event;

public class PitDamage extends Event {

    public PitDamage() {
        super(27, 16);
        this.setCooldown(60);
    }

    @Override
    public void event() {
        corePanel.setDialogueState(new String[]{"You fall into a pit stupid"});
        Health h = (Health) corePanel.getPlayer().getStat(Stats.StatType.DAMAGE);
        h.applyDamage(1, 10);
    }
}
