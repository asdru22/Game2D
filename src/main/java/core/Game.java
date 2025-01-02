package core;

import io.KeyHandler;

import javax.swing.*;

public class Game extends JFrame implements GameLoop {

    private GamePanel currentPanel;

    private final KeyHandler keyHandler;

    public Game() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.keyHandler = new KeyHandler();

        //Sound.soundOff();

//        currentPanel = new CorePanel(this);
        currentPanel = new Title(this);
        this.add(currentPanel);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void setCurrentPanel(GamePanel panel) {
        this.currentPanel = panel;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void removeCurrentPanel() {
        this.remove(currentPanel);
        currentPanel = null;
    }

    public GamePanel getCurrentPanel() {
        return currentPanel;
    }

    @Override
    public boolean canRun() {
        return true;
    }

    @Override
    public void update() {
        currentPanel.decreaseInputDelay();
        currentPanel.loop();
    }

    @Override
    public void repaint() {
        currentPanel.repaint();
    }

    @Override
    public void setGameTitle(String title) {

    }
}
