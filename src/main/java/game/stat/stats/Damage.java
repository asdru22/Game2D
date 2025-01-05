package game.stat.stats;

import game.entity.Entity;
import game.stat.Stat;

public class Damage extends Stat {
    public Damage(int amount) {
        super(amount);
    }

    public void dealDamage(Entity target, int invulnerabilityTime) {
        Health h = target.getStats().getStat(Health.class);
        h.applyDamage(this.getAmount(), invulnerabilityTime);
    }

    public void dealDamage(Entity target){
        dealDamage(target, 0);
    }

}
