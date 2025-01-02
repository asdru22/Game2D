package core;

import io.KeyHandler;

import javax.swing.*;
import java.awt.*;

public abstract class GamePanel extends JPanel implements GameLoop {

    private final Thread panelThread;
    protected final Game game;
    protected KeyHandler keyHandler;

    protected int inputDelay = 0;

    public GamePanel(Game game) {
        super();

        this.game = game;

        this.setPreferredSize(new Dimension(
                ScreenSettings.getScreenWidth(),
                ScreenSettings.getScreenHeight()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(game.getKeyHandler());

        init();

        this.panelThread = new Thread(this);
        panelThread.start();
    }

    protected abstract void init();

    public void changeScene(GamePanel newPanel) {
        game.removeCurrentPanel();
        game.setCurrentPanel(newPanel);
        game.add(newPanel);
        game.revalidate();
        game.repaint();
    }
}
