package game.entity;

import core.GamePanel;
import core.PanelSettings;
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
}

