package game.entity.npc;

import core.CorePanel;
import game.entity.Collidable;
import game.entity.Direction;
import game.entity.Entity;
import game.entity.NPC;

import java.util.Random;

public class OldMan extends NPC implements Collidable {

    private final String[] dialogues = new String[]{
            "Hello, I am an old man",
            "So you are a newbie, right?",
            "I used to be a wizard but now i just do crack. ",
            "Welp, see ya."
    };

    public OldMan(int worldX, int worldY, CorePanel corePanel) {
        super(worldX, worldY, 1, "old_man", corePanel);
        setActionInterval(120);
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
        getCorePanel().setDialogueState(dialogues);
    }
}
