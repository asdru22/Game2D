package game.entity.npc;

import core.CorePanel;
import game.entity.Direction;
import game.entity.NPC;

import java.util.Random;

public class OldMan extends NPC {

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
    
}
