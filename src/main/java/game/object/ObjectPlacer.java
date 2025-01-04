package game.object;

import core.CorePanel;
import game.entity.Entity;
import game.entity.enemy.GreenSlime;
import game.entity.npc.OldMan;

public class ObjectPlacer {
    private final CorePanel corePanel;

    public ObjectPlacer(CorePanel corePanel) {
        this.corePanel = corePanel;

        this.addEntity(new OldMan(21, 21, corePanel));
        this.addEntity(new GreenSlime(23,36,corePanel));
        this.addEntity(new GreenSlime(23,37,corePanel));

    }


    private void addEntity(Entity entity) {
        corePanel.getGameObjects().addEntity(entity);
    }

    private void addObject(BaseObject object) {
        corePanel.getGameObjects().addObject(object);
    }
}
