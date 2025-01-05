package game.entity.npc;

import core.CorePanel;
import game.entity.*;
import game.stat.stats.Speed;

import java.util.Random;

public class OldMan extends NPC implements Collidable {

    private final String[] dialogues = new String[]{
            "Hello, I am an old man",
            "So you are a newbie, right?",
            "I used to be a wizard but now i just do crack. ",
            "Welp, see ya."
    };

    public OldMan(int worldX, int worldY, CorePanel corePanel) {
        super(worldX, worldY, "old_man", corePanel);
        this.setActionInterval(120);
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
        if ((entity instanceof Player)) {
            getCorePanel().setDialogueState(dialogues);
        }
    }

    @Override
    public void addAnimations() {
        animations.add(AnimationHandler.Animations.WalkingUp, "walking/up", 2);
        animations.add(AnimationHandler.Animations.WalkingDown, "walking/down", 2);
        animations.add(AnimationHandler.Animations.WalkingLeft, "walking/left", 2);
        animations.add(AnimationHandler.Animations.WalkingRight, "walking/right", 2);
    }

    @Override
    public void setStats() {
        stats.addStat(new Speed(1));
    }
}
