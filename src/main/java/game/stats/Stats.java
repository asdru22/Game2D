package game.stats;

import game.entity.Entity;

public class Stats {

    private int health, maxHealth, damage, speed;
    private int invulnerabilityTime = 0;

    public Stats(int speed) {
        this.speed = speed;
        this.maxHealth = -1;
        this.health = -1;
        this.damage = -1;
    }

    public Stats(int health, int damage, int speed) {
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.speed = speed;
    }

    public void decreaseInvulnerabilityTime() {
        if (invulnerabilityTime > 0) {
            invulnerabilityTime--;
        }
    }

    public void dealDamage(Entity target, int invulnerabilityTime) {
        target.getStats().applyDamage(damage, invulnerabilityTime);
    }

    public void applyDamage(int damage, int invulnerabilityTime) {
        if (this.invulnerabilityTime == 0) {
            this.health -= damage;
            this.invulnerabilityTime = invulnerabilityTime;
            System.out.println(this.invulnerabilityTime);
        }
    }

    public void addSpeed(int amount) {
        this.speed += amount;
    }

    public void applyDamage(int damage) {
        applyDamage(damage, 0);
    }

    public void heal(int healAmount) {
        this.health = Math.min(this.health + healAmount, maxHealth);
    }

    public void fullHeal() {
        heal(maxHealth);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isInvulnerable() {
        return invulnerabilityTime > 0;
    }

    public int getInvulnerabilityTime() {
        return invulnerabilityTime;
    }
}
