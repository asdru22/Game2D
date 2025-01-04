package game.stat.stats;

import game.stat.CappedStat;
import game.stat.Stats;

public class Health extends CappedStat {

    private int invulnerabilityTime = 0;

    public Health(int amount) {
        super(amount);
    }

    public void heal(int healAmount) {
        this.amount = Math.min(this.amount + healAmount, max);
    }

    public void receiveDamage(int damage) {
        this.amount -= damage;
    }

    public void applyDamage(int damage) {
        applyDamage(damage, 0);
    }

    public void applyDamage(int damage, int invulnerabilityTime) {
        if (!isInvulnerable()) {
            receiveDamage(damage);
            setInvulnerabilityTime(invulnerabilityTime);
        }
    }

    public void fullHeal() {
        heal(max);
    }

    public void decreaseInvulnerabilityTime() {
        if (invulnerabilityTime > 0) {
            invulnerabilityTime--;
        }
    }

    public boolean isInvulnerable() {
        return invulnerabilityTime > 0;
    }

    public void setInvulnerabilityTime(int invulnerabilityTime) {
        this.invulnerabilityTime = invulnerabilityTime;
    }

}
