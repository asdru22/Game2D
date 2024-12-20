package core;

import game.AssetPlacer;
import game.entity.CollisionChecker;
import game.entity.Player;
import game.object.BaseObject;
import io.KeyHandler;
import game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private final Thread gameThread;
    private final KeyHandler keyHandler;
    private final Game game;
    private int fps;
    private final CollisionChecker collisionChecker;
    private final TileManager tileManager;
    private static int TILE_SIZE;
    private final AssetPlacer assetPlacer;

    Player p;
    public ArrayList<BaseObject> objs = new ArrayList<>();

    public GamePanel(Game game) {
        super();
        this.keyHandler = new KeyHandler();
        this.game = game;

        TILE_SIZE = PanelSettings.getTileSize();

        this.collisionChecker = new CollisionChecker(this);
        this.assetPlacer = new AssetPlacer(this);
        // panel settings
        this.setPreferredSize(new Dimension(
                PanelSettings.getScreenWidth(), PanelSettings.getScreenHeight()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improves performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);


        p = new Player(this, keyHandler);
        tileManager = new TileManager(this);
        assetPlacer.setObject();

        // game thread
        this.gameThread = new Thread(this);
        this.gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 10e8 / PanelSettings.getTargetFPS(), delta = 0;
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
                game.setTitle(String.format("%s (%dFPS)", PanelSettings.getName(), fps));
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

    public Player getPlayer() {
        return p;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public static int getTileSize(){
        return TILE_SIZE;
    }
}
