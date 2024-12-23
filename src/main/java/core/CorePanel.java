package core;

import game.UI;
import game.entity.CollisionChecker;
import game.entity.Player;
import game.object.BaseObject;
import game.object.ObjectPlacer;
import game.tile.TileManager;
import io.KeyHandler;
import io.Sound;
import io.SoundType;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CorePanel extends JPanel implements Runnable {

    private CollisionChecker collisionChecker;
    private TileManager tileManager;
    private ObjectPlacer objectPlacer;
    private KeyHandler keyHandler;
    private UI ui;

    private final Thread gameThread;
    private final CoreFrame coreFrame;

    private GameState gameState;
    private int pauseDelay = 0;
    private GameObjects gameObjects;

    public CorePanel(CoreFrame coreFrame) {
        super();
        this.coreFrame = coreFrame;
        this.gameThread = new Thread(this);

        initializeObjects();
        initializePanel();
        start();
    }

    private void initializeObjects() {
        this.gameState = GameState.PLAYING;
        // IMPORTANT ORDER
        this.keyHandler = new KeyHandler();

        this.gameObjects = new GameObjects(this);

        this.collisionChecker = new CollisionChecker(this);
        this.tileManager = new TileManager(this);
        this.objectPlacer = new ObjectPlacer(this);

        this.ui = new UI(this);
    }

    private void initializePanel() {
        this.setPreferredSize(new Dimension(
                PanelSettings.getScreenWidth(), PanelSettings.getScreenHeight()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improves performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    private void start() {
        Sound.loop(SoundType.MUSIC);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 10e8 / PanelSettings.getTargetFPS(), delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0, timeDelta;
        byte fps = 0;
        while (gameState != GameState.FINISHED) {

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
                coreFrame.setTitle(String.format("%s (%dFPS)", PanelSettings.getName(), fps));
                fps = 0;
                timer = 0;
            }
        }
    }

    private void update() {

        if(pauseDelay>0) pauseDelay--;

        switch (gameState) {
            case PLAYING -> playing();
            case PAUSED -> paused();
        }
    }

    private void playing() {
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE) && pauseDelay == 0) {
            gameState = GameState.PAUSED;
            pauseDelay = 30;
            return;
        }
        gameObjects.update();
    }

    private void paused() {
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE) && pauseDelay == 0) {
            gameState = GameState.PLAYING;
            pauseDelay = 30;
            return;
        }
        // paused stuff
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        gameObjects.draw(g2d);

        ui.draw(g2d);

        g2d.dispose();
    }

    public Player getPlayer() {
        return gameObjects.getPlayer();
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }


    public TileManager getTileManager() {
        return tileManager;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public UI getGameUI() {
        return ui;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
