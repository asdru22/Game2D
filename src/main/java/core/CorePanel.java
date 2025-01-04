package core;

import core.impl.GameObjects;
import core.impl.GameState;
import core.impl.PanelObjects;
import ui.MessageBox;
import ui.UI;
import game.entity.CollisionChecker;
import game.entity.Player;
import game.tile.TileManager;
import io.KeyHandler;
import io.Sound;
import io.SoundType;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CorePanel extends GamePanel {

    private GameState gameState;
    private GameObjects gameObjects;
    private PanelObjects panelObjects;

    public CorePanel(Game game) {
        super(game);
    }

    @Override
    protected void init() {
        Sound.loop(SoundType.MUSIC);
        this.gameState = GameState.PLAYING;
        // IMPORTANT ORDER
        this.keyHandler = game.getKeyHandler();
        this.gameObjects = new GameObjects(this);

        this.panelObjects = new PanelObjects(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        panelObjects.getTileManager().draw(g2d);

        gameObjects.draw(g2d);

        panelObjects.getUI().draw(g2d);

        if (gameState == GameState.DIALOGUE) {
            panelObjects.getMessageBox().draw(g2d);
        }

        g2d.dispose();
    }

    @Override
    public void loop() {
        switch (gameState) {
            case PLAYING -> playing();
            case PAUSED -> paused();
            case DIALOGUE -> dialogue();
        }
    }

    private void dialogue() {
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER) && inputDelay == 0) {
            inputDelay = 20;
            panelObjects.getMessageBox().nextMessage();
        }
    }

    private void playing() {

        if (keyHandler.isKeyPressed(KeyEvent.VK_G) && inputDelay == 0) {
            getPlayer().getStats().applyDamage(1);
            inputDelay = 30;
            return;
        }

        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE) && inputDelay == 0) {
            gameState = GameState.PAUSED;
            inputDelay = 30;
            return;
        }
        gameObjects.update();
    }

    private void paused() {
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE) && inputDelay == 0) {
            gameState = GameState.PLAYING;
            inputDelay = 30;
            return;
        }
        // paused stuff
    }

    public Player getPlayer() {
        return gameObjects.getPlayer();
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public TileManager getTileManager() {
        return panelObjects.getTileManager();
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public CollisionChecker getCollisionChecker() {
        return panelObjects.getCollisionChecker();
    }

    public UI getGameUI() {
        return panelObjects.getUI();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public MessageBox getDialogueBox() {
        return panelObjects.getMessageBox();
    }

    public void setDialogueState(String[] dialogues) {
        gameState = GameState.DIALOGUE;
        panelObjects.getMessageBox().addMessages(dialogues);
    }
}
