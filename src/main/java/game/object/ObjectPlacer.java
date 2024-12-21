package game.object;

import core.CorePanel;

public class ObjectPlacer {
    private final CorePanel corePanel;

    public ObjectPlacer(CorePanel corePanel) {
        this.corePanel = corePanel;
    }

    public void setObject() {

        corePanel.addGameObject(new Key(23, 7));
        corePanel.addGameObject(new Key(23, 40));
        corePanel.addGameObject(new Key(37, 7));

        corePanel.addGameObject(new Door(10, 11));
        corePanel.addGameObject(new Door(8, 28));
        corePanel.addGameObject(new Door(12, 22));

        corePanel.addGameObject(new Chest(10, 7));

        corePanel.addGameObject(new Boots(37, 42));

    }
}
