package game.stat;

import game.entity.Entity;
import game.stat.stats.Health;

import java.util.EnumMap;
import java.util.Map;

public class Stats {

    public enum StatType {
        HEALTH,
        DAMAGE,
        SPEED
    }

    private final Entity entity;
    private final Map<StatType, Stat> stats = new EnumMap<>(StatType.class);

    public Stats(Entity entity) {
        this.entity = entity;
    }

    public void add(StatType type, Stat stat) {
        stat.setEntity(entity);
        this.stats.put(type, stat);
    }

    public Map<StatType, Stat> get() {
        return stats;
    }

    public boolean isInvulnerable() {
        Health h = (Health) stats.get(StatType.HEALTH);
        return h != null && h.isInvulnerable();
    }
}
