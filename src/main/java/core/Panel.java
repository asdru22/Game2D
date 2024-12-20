package core;

import game.AssetSetter;
import game.entity.CollisionChecker;
import game.entity.Player;
import game.object.BaseObject;
import io.KeyHandler;
import game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {

    private final PanelSettings panelSettings;
    private final Thread gameThread;
    private final KeyHandler keyHandler;
    private final Game game;
    private int fps;
    private final WorldSettings worldSettings;
    private final CollisionChecker collisionChecker;
    private final TileManager tileManager;

    private final AssetSetter assetSetter;

    Player p;
    public ArrayList<BaseObject> objs = new ArrayList<>();

    public Panel(Game game) {
        super();
        this.panelSettings = new PanelSettings();
        this.worldSettings = new WorldSettings(panelSettings.getTileSize());
        this.keyHandler = new KeyHandler();
        this.game = game;
        this.collisionChecker = new CollisionChecker(this);
        this.assetSetter = new AssetSetter(this);
        // panel settings
        this.setPreferredSize(new Dimension(
                panelSettings.getScreenWidth(), panelSettings.getScreenHeight()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improves performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);


        p = new Player(this, keyHandler);
        tileManager = new TileManager(this);
        assetSetter.setObject();

        // game thread
        this.gameThread = new Thread(this);
        this.gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 10e8 / this.panelSettings.getTargetFPS(), delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0, timeDelta;
        byte fps = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();
            timeDelta = currentTime - lastTime;
            delta += timeDelta / drawInterval;
            timer += timeDelta;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                fps++;
            }

            if (timer >= 10e8) {
                this.fps = fps;
                game.setTitle(String.format("%s (%dFPS)", this.panelSettings.getName(), fps));
                fps = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        p.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        for (BaseObject b : objs) {
            if (b != null) b.draw(g2d, this);
        }

        p.draw(g2d);

        g2d.dispose();
    }

    public PanelSettings getPanelSettings() {
        return panelSettings;
    }

    public WorldSettings getWorldSettings() {
        return worldSettings;
    }

    public Player getPlayer() {
        return p;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
