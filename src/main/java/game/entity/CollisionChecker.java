package game.entity;

import core.CorePanel;
import core.PanelSettings;
import game.object.BaseObject;
import game.tile.TileManager;

import java.util.ArrayList;

public class CollisionChecker {
    private final CorePanel corePanel;

    public CollisionChecker(CorePanel corePanel) {
        this.corePanel = corePanel;
    }

    public void checkTile(Entity entity) {
        final int tileSize = PanelSettings.getTileSize();
        int entityLeft = entity.worldX + entity.hitbox.x;
        int entityRight = entityLeft + entity.hitbox.width;
        int entityTop = entity.worldY + entity.hitbox.y;
        int entityBottom = entityTop + entity.hitbox.height;

        TileManager tileManager = corePanel.getTileManager();

        // Check UP
        if (entity.direction.contains(Direction.UP)) {
            int leftCol = entityLeft / tileSize;
            int rightCol = entityRight / tileSize;
            int topRow = (entityTop - entity.speed) / tileSize;

            int tileNum1 = tileManager.getTileNum(leftCol, topRow);
            int tileNum2 = tileManager.getTileNum(rightCol, topRow);

            if (tileManager.getTiles().get(tileNum1).hasCollision() ||
                    tileManager.getTiles().get(tileNum2).hasCollision()) {
                entity.setColliding(Direction.UP);
            }
        }

        // Check DOWN
        if (entity.direction.contains(Direction.DOWN)) {
            int leftCol = entityLeft / tileSize;
            int rightCol = entityRight / tileSize;
            int bottomRow = (entityBottom + entity.speed) / tileSize;

            int tileNum1 = tileManager.getTileNum(leftCol, bottomRow);
            int tileNum2 = tileManager.getTileNum(rightCol, bottomRow);

            if (tileManager.getTiles().get(tileNum1).hasCollision() ||
                    tileManager.getTiles().get(tileNum2).hasCollision()) {
                entity.setColliding(Direction.DOWN);
            }
        }

        // Check LEFT
        if (entity.direction.contains(Direction.LEFT)) {
            int leftCol = (entityLeft - entity.speed) / tileSize;
            int topRow = entityTop / tileSize;
            int bottomRow = entityBottom / tileSize;

            int tileNum1 = tileManager.getTileNum(leftCol, topRow);
            int tileNum2 = tileManager.getTileNum(leftCol, bottomRow);

            if (tileManager.getTiles().get(tileNum1).hasCollision() ||
                    tileManager.getTiles().get(tileNum2).hasCollision()) {
                entity.setColliding(Direction.LEFT);
            }
        }

        // Check RIGHT
        if (entity.direction.contains(Direction.RIGHT)) {
            int rightCol = (entityRight + entity.speed) / tileSize;
            int topRow = entityTop / tileSize;
            int bottomRow = entityBottom / tileSize;

            int tileNum1 = tileManager.getTileNum(rightCol, topRow);
            int tileNum2 = tileManager.getTileNum(rightCol, bottomRow);

            if (tileManager.getTiles().get(tileNum1).hasCollision() ||
                    tileManager.getTiles().get(tileNum2).hasCollision()) {
                entity.setColliding(Direction.RIGHT);
            }
        }
    }

    public void checkObject(Entity entity) {
        BaseObject[] c = new BaseObject[1];

        for (BaseObject obj : corePanel.getGameObjects().getObjects()) {
            entity.hitbox.x = entity.worldX + entity.hitbox.x;
            entity.hitbox.y = entity.worldY + entity.hitbox.y;

            obj.hitbox.x = obj.worldX + obj.hitbox.x;
            obj.hitbox.y = obj.worldY + obj.hitbox.y;

            if (entity.direction.contains(Direction.UP)) {
                entity.hitbox.y -= entity.speed;
                collidingWithObject(entity, obj, Direction.UP, c);
            }
            if (entity.direction.contains(Direction.DOWN)) {
                entity.hitbox.y += entity.speed;
                collidingWithObject(entity, obj, Direction.DOWN, c);
            }
            if (entity.direction.contains(Direction.LEFT)) {
                entity.hitbox.x -= entity.speed;
                collidingWithObject(entity, obj, Direction.LEFT, c);
            }
            if (entity.direction.contains(Direction.RIGHT)) {
                entity.hitbox.x += entity.speed;
                collidingWithObject(entity, obj, Direction.RIGHT, c);
            }

            entity.hitbox.x = entity.hitboxX;
            entity.hitbox.y = entity.hitboxY;
            obj.hitbox.x = obj.hitboxX;
            obj.hitbox.y = obj.hitboxY;
        }
    }

    private void collidingWithObject(Entity e, BaseObject obj,
                                     Direction dir, BaseObject[] collidedWith) {
        if (e.hitbox.intersects(obj.hitbox)) {
            collidedWith[0] = obj;
            obj.onCollision(corePanel);
            if (obj.isCollideable()) {
                e.collisions.add(dir);
            }
        }
    }

    public void checkEntity(Entity entity, ArrayList<Entity> target){
        Entity[] e = new Entity[1];

        for (Entity t : target) {
            if(!t.equals(entity)){
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                t.hitbox.x = t.worldX + t.hitbox.x;
                t.hitbox.y = t.worldY + t.hitbox.y;

                if (entity.direction.contains(Direction.UP)) {
                    entity.hitbox.y -= entity.speed;
                    collidingWithEntity(entity, t, Direction.UP, e);
                }
                if (entity.direction.contains(Direction.DOWN)) {
                    entity.hitbox.y += entity.speed;
                    collidingWithEntity(entity, t, Direction.DOWN, e);
                }
                if (entity.direction.contains(Direction.LEFT)) {
                    entity.hitbox.x -= entity.speed;
                    collidingWithEntity(entity, t, Direction.LEFT, e);
                }
                if (entity.direction.contains(Direction.RIGHT)) {
                    entity.hitbox.x += entity.speed;
                    collidingWithEntity(entity, t, Direction.RIGHT, e);
                }

                entity.hitbox.x = entity.hitboxX;
                entity.hitbox.y = entity.hitboxY;
                t.hitbox.x = t.hitboxX;
                t.hitbox.y = t.hitboxY;
            }
        }

    }

    private void collidingWithEntity(Entity e, Entity t,
                                     Direction dir, Entity[] collidedWith) {
        if (e.hitbox.intersects(t.hitbox)) {
            collidedWith[0] = t;
            e.onCollision();
            e.collisions.add(dir);
        }
    }

}

