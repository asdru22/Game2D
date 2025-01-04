package game.worldevent;

import core.CorePanel;
import game.entity.Player;
import game.worldevent.event.HealingPool;
import game.worldevent.event.PitDamage;

import java.util.ArrayList;
import java.util.Iterator;

public class WorldEvents {

    private final CorePanel corePanel;

    private final ArrayList<Event> events;

    public WorldEvents(CorePanel corePanel) {
        this.corePanel = corePanel;
        events = new ArrayList<>();
        setEvents();
        events.forEach(event -> event.initialize(corePanel));
    }

    private void setEvents() {
        events.add(new HealingPool());
        events.add(new PitDamage());
    }

    public void update() {
        Player player = corePanel.getGameObjects().getPlayer();
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event e = iterator.next();
            if (e.decreaseCooldown() == 0) {
                e.checkTrigger(player,iterator);
            }
        }
    }
}
