package _test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ActorStats implements Component {
    private final Map<String, Integer> stats = new HashMap<>();

    public void setStat(String statName, int value) {
        stats.put(statName, value);
    }

    public int getStat(String statName) {
        return stats.getOrDefault(statName, 0);
    }

    public boolean hasStat(String statName) {
        return stats.containsKey(statName);
    }

    public Map<String, Integer> getAllStats() {
        return Collections.unmodifiableMap(stats);
    }
}
