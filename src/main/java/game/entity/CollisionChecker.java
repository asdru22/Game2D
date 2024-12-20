package game.entity;

import core.GamePanel;
import core.PanelSettings;
import game.object.BaseObject;
import game.tile.TileManager;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        final int tileSize = PanelSettings.getTileSize();
        int entityLeft = entity.worldX + entity.hitbox.x;
        int entityRight = entityLeft + entity.hitbox.width;
        int entityTop = entity.worldY + entity.hitbox.y;
        int entityBottom = entityTop + entity.hitbox.height;

        TileManager tileManager = gamePanel.getTileManager();

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

    public BaseObject checkObject(Entity entity) {
        BaseObject[] c = new BaseObject[1];

        for (BaseObject obj : gamePanel.objs) {
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

        return c[0];
    }

    private void collidingWithObject(Entity e, BaseObject obj,
                                     Direction dir, BaseObject[] collidedWith) {
        if (e.hitbox.intersects(obj.hitbox)) {
            collidedWith[0] = obj;
            if (obj.isCollideable()) {
                e.collisions.add(dir);
            }
        }

    }

}

