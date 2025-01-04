package game.stat.stats;

import game.entity.Entity;
import game.stat.Stat;
import game.stat.Stats;


public class Damage extends Stat {
    public Damage(int amount) {
        super(amount);
    }

    public void dealDamage(Entity target, int invulnerabilityTime) {
        Health h = (Health) target.getStatsMap().get(Stats.StatType.HEALTH);
        h.applyDamage(this.getAmount(), invulnerabilityTime);
    }

    public void dealDamage(Entity target){
        dealDamage(target, 0);
    }

}
