package game.stat;

import game.entity.Entity;
import game.stat.stats.Health;

import java.util.HashMap;
import java.util.Map;

public class Stats {

    private final Entity entity;
    private final Map<Class<? extends Stat>, Stat> stats = new HashMap<>();

    public Stats(Entity entity) {
        this.entity = entity;
    }

    public <T extends Stat> void addStat(T stat) {
        stats.put(stat.getClass(), stat);
    }

    public <T extends Stat> T getStat(Class<T> statClass) {
        return statClass.cast(stats.get(statClass));
    }

    public boolean hasStat(Class<? extends Stat> statClass) {
        return stats.containsKey(statClass);
    }

    public boolean isInvulnerable() {
        Health h = (Health) stats.get(Health.class);
        return h != null && h.isInvulnerable();
    }
}
