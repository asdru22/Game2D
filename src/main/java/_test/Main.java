package _test;

public class Main {
    public static void main(String[] args) {
        Entity player = new Entity();
        ActorStats stats = new ActorStats();
        stats.setStat("strength", 10);
        stats.setStat("agility", 15);

        player.addComponent(stats);

        // Retrieve and modify stats
        player.getComponent(ActorStats.class).ifPresent(actorStats -> {
            System.out.println("Strength: " + actorStats.getStat("strength")); // Output: 10
            actorStats.setStat("strength", 12);
        });
    }
}
