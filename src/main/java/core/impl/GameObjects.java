package core.impl;

import core.CorePanel;
import game.entity.Entity;
import game.entity.Player;
import game.object.BaseObject;
import game.worldevent.WorldEvents;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class GameObjects {

    private final CorePanel corePanel;

    private final Player player;
    private final ArrayList<BaseObject> objects = new ArrayList<>();
    private final ArrayList<Entity> entities = new ArrayList<>();
    private final WorldEvents worldEvents;

    public GameObjects(CorePanel corePanel) {
        player = new Player(corePanel, corePanel.getKeyHandler());
        entities.add(player);
        this.corePanel = corePanel;
        worldEvents = new WorldEvents(corePanel);
    }

    public void update() {
        entities.forEach(e -> {
            if (e != null) {
                e.mainLoop();
            }
        });
        worldEvents.update();
    }

    public void draw(Graphics2D g2d) {

        objects.forEach(obj -> {
            if (obj != null) {
                obj.draw(g2d, corePanel);
            }
        });

        entities.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Entity::getWorldY))
                .forEach(e -> e.draw(g2d));
    }

    public Player getPlayer() {
        return player;
    }

    public void addObject(BaseObject object) {
        objects.add(object);
    }

    public ArrayList<BaseObject> getObjects() {
        return objects;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public WorldEvents getEvents() {
        return worldEvents;
    }
}
