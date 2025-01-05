package game.entity.enemy;

import core.CorePanel;
import game.entity.*;
import game.stat.Stats;
import game.stat.stats.Damage;
import game.stat.stats.Health;
import game.stat.stats.Speed;

import java.util.Random;

public class GreenSlime extends Enemy implements Collidable {

    public GreenSlime(int worldX, int worldY, CorePanel corePanel) {
        super(worldX, worldY, "green_slime", corePanel);
        this.setHitbox(3, 18, 14, 10);
        this.setActionInterval(120);
    }

    @Override
    public void addAnimations() {
        animations.add(AnimationHandler.Animations.WalkingUp, "moving", 2);
        animations.add(AnimationHandler.Animations.WalkingDown, "moving", 2);
        animations.add(AnimationHandler.Animations.WalkingLeft, "moving", 2);
        animations.add(AnimationHandler.Animations.WalkingRight, "moving", 2);
    }

    @Override
    public void setStats() {
        stats.add(Stats.StatType.DAMAGE, new Damage(2));
        stats.add(Stats.StatType.HEALTH, new Health(10));
        stats.add(Stats.StatType.SPEED, new Speed(1));
    }

    @Override
    public void loop() {
        Random random = new Random();
        int i = random.nextInt(4);

        direction.clear();

        direction.add(Direction.values()[i]);
    }

    @Override
    public void onCollision(Entity entity) {
        if (!(entity instanceof Player)) return;
        Damage d = (Damage) this.getStat(Stats.StatType.DAMAGE);
        d.dealDamage(entity, 50);
    }
}