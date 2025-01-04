package core.impl;

import core.CorePanel;
import game.entity.CollisionChecker;
import game.object.ObjectPlacer;
import game.tile.TileManager;
import ui.MessageBox;
import ui.UI;

public class PanelObjects {

    private final CollisionChecker collisionChecker;
    private final TileManager tileManager;
    private final ObjectPlacer objectPlacer;
    private final UI ui;
    private final MessageBox messageBox;

    public PanelObjects(CorePanel panel) {
        this.collisionChecker = new CollisionChecker(panel);
        this.tileManager = new TileManager(panel);
        this.objectPlacer = new ObjectPlacer(panel);
        this.ui = new UI(panel);
        this.messageBox = new MessageBox(panel);}

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public ObjectPlacer getObjectPlacer() {
        return objectPlacer;
    }

    public UI getUI() {
        return ui;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }
}
