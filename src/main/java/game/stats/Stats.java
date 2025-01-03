package game.stats;

public class Stats {

    public int health, maxHealth, damage, speed;

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
}
