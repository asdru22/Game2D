package core;

import ui.DialogueBox;
import ui.UI;
import game.entity.CollisionChecker;
import game.entity.Player;
import game.object.ObjectPlacer;
import game.tile.TileManager;
import io.KeyHandler;
import io.Sound;
import io.SoundType;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CorePanel extends GamePanel {

    private CollisionChecker collisionChecker;
    private TileManager tileManager;
    private ObjectPlacer objectPlacer;
    private UI ui;
    private DialogueBox dialogueBox;

    private GameState gameState;
    private GameObjects gameObjects;

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
        this.collisionChecker = new CollisionChecker(this);
        this.tileManager = new TileManager(this);
        this.objectPlacer = new ObjectPlacer(this);
        this.ui = new UI(this);
        this.dialogueBox = new DialogueBox(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        gameObjects.draw(g2d);

        ui.draw(g2d);

        if (gameState == GameState.DIALOGUE) {
            dialogueBox.draw(g2d);
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
            dialogueBox.nextMessage();
        }
    }

    private void playing() {
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

    public DialogueBox getDialogueBox() {
        return dialogueBox;
    }

    public void setDialogueState(String[] dialogues) {
        gameState = GameState.DIALOGUE;
        dialogueBox = new DialogueBox(this);
        dialogueBox.addMessages(dialogues);
    }
}
