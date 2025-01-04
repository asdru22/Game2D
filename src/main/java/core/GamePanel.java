package core;

import core.impl.ScreenSettings;
import io.KeyHandler;

import javax.swing.*;
import java.awt.*;

public abstract class GamePanel extends JPanel {

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

    }

    protected abstract void init();

    public abstract void loop();

    public void changeScene(GamePanel newPanel) {
        game.removeCurrentPanel();
        game.setCurrentPanel(newPanel);
        game.add(newPanel);
        game.revalidate();
        game.repaint();
        newPanel.requestFocusInWindow(); // Ensure the new panel gains focus
    }

    public void decreaseInputDelay() {
        if (inputDelay > 0) inputDelay--;
    }
}
