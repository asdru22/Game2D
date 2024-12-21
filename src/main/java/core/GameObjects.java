package core;

import game.entity.Player;
import game.object.BaseObject;

import java.awt.*;
import java.util.ArrayList;

public class GameObjects {

    private final CorePanel corePanel;

    private final Player player;
    private final ArrayList<BaseObject> objects = new ArrayList<>();

    public GameObjects(CorePanel corePanel) {
        player = new Player(corePanel, corePanel.getKeyHandler());
        this.corePanel = corePanel;
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D g2d) {

        objects.forEach(obj->{
            if (obj != null) {
                obj.draw(g2d, corePanel);
            }
        });

        player.draw(g2d);
    }

    public Player getPlayer() {
        return player;
    }

    public void addGameObject(BaseObject object) {
        objects.add(object);
    }

    public ArrayList<BaseObject> getObjects() {
        return objects;
    }
}
