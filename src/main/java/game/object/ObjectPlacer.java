package game.object;

import core.CorePanel;
import game.entity.Entity;
import game.entity.npc.OldMan;

public class ObjectPlacer {
    private final CorePanel corePanel;

    public ObjectPlacer(CorePanel corePanel) {
        this.corePanel = corePanel;

        this.addNPC(new OldMan(21, 21, corePanel));
    }


    private void addNPC(Entity entity) {
        corePanel.getGameObjects().addEntity(entity);
    }

    private void addObject(BaseObject object) {
        corePanel.getGameObjects().addObject(object);
    }
}
