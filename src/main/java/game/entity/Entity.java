package game.entity;

import core.CorePanel;
import core.impl.ScreenSettings;
import game.stat.Stats;
import game.stat.stats.Health;
import game.stat.stats.Speed;
import math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;

public abstract class Entity extends TileEntity implements Drawable {

    protected final EnumSet<Direction> direction = EnumSet.noneOf(Direction.class);
    protected final EnumSet<Direction> collisions = EnumSet.noneOf(Direction.class);
    protected final Stats stats;

    private final CorePanel corePanel;
    protected final AnimationHandler animations;

    public Entity(int worldX, int worldY, String id, CorePanel corePanel) {
        super(id, worldX, worldY);
        this.corePanel = corePanel;

        resetCollisions();
        direction.add(Direction.DOWN);

        animations = new AnimationHandler(this);
        addAnimations();
        animations.initialize();
        this.stats = new Stats(this);
        setStats();
    }

    @Override
    public void draw(Graphics2D g2d) {

//        g2d.setColor(Color.RED);
//        g2d.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        Player p = corePanel.getPlayer();
        final int tileSize = ScreenSettings.getTileSize();

        int screenX = worldX - p.getWorldX() + p.getScreenX();
        int screenY = worldY - p.getWorldY() + p.getScreenY();
        if (worldX + tileSize > p.getWorldX() - p.getScreenX() &&
                worldX - tileSize < p.getWorldX() + p.getScreenX() &&
                worldY + tileSize > p.getWorldY() - p.getScreenY() &&
                worldY - tileSize < p.getWorldY() + p.getScreenY()
        ) {

            updateAnimation();
            BufferedImage image = animations.getCurrentAnimation().getCurrentFrame();
            drawImage(g2d, image, screenX, screenY, tileSize);
        }
    }

    protected void drawImage(Graphics2D g2d, BufferedImage image, int screenX, int screenY, int tileSize) {
        if (this.getStats().isInvulnerable()) {
            drawInvulnerable(g2d, image,
                    screenX, screenY, tileSize);
        } else {
            g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }

    private void drawInvulnerable(Graphics2D g2d, BufferedImage image, int screenX,
                                  int screenY, int tileSize) {

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void move() {
        // check tile collision
        this.resetCollisions();
        CollisionChecker cc = this.getCorePanel().getCollisionChecker();
        cc.checkTile(this);
        cc.checkEntity(this, this.getCorePanel().getGameObjects().getEntities());
        cc.checkObject(this);

        Vector2f s = new Vector2f();

        if (this.isNotColliding(Direction.UP) && direction.contains(Direction.UP)) {
            s.y -= 1;
        }
        if (this.isNotColliding(Direction.DOWN) && direction.contains(Direction.DOWN)) {
            s.y += 1;
        }
        if (this.isNotColliding(Direction.LEFT) && direction.contains(Direction.LEFT)) {
            s.x -= 1;
        }
        if (this.isNotColliding(Direction.RIGHT) && direction.contains(Direction.RIGHT)) {
            s.x += 1;
        }

        s.normalize();
        s.multiply(stats.getStat(Speed.class).getAmount());
        if (s.x != 0 || s.y != 0) {
            this.getAnimations().update();
            this.worldX += (int) s.x;
            this.worldY += (int) s.y;
        }
    }

    public void mainLoop() {
        Health h = stats.getStat(Health.class);
        if (h != null) {
            h.decreaseInvulnerabilityTime();
        }
        update();
    }


    public void updateAnimation() {
        if (direction.contains(Direction.UP))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingUp);
        if (direction.contains(Direction.DOWN))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingDown);
        if (direction.contains(Direction.LEFT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingLeft);
        if (direction.contains(Direction.RIGHT))
            this.getAnimations().setCurrent(AnimationHandler.Animations.WalkingRight);
    }

    public abstract void update();

    public abstract void addAnimations();

    public abstract void setStats();

    public EnumSet<Direction> getDirections() {
        return direction;
    }

    public Stats getStats() {
        return stats;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public boolean isNotColliding(Direction direction) {
        return !collisions.contains(direction);
    }

    public void setColliding(Direction direction) {
        collisions.add(direction);
    }

    public void resetCollisions() {
        collisions.clear();
    }

    public AnimationHandler getAnimations() {
        return animations;
    }

    public CorePanel getCorePanel() {
        return corePanel;
    }
}


