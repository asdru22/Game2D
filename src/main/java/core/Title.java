package core;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Title extends GamePanel {

    public Title(Game game) {
        super(game);
    }

    @Override
    protected void init() {
        this.keyHandler = game.getKeyHandler();
    }

    @Override
    public boolean canRun() {
        return true;
    }

    @Override
    public void update() {
        if (inputDelay > 0) inputDelay--;

        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER) && inputDelay == 0) {
            inputDelay = 20;
            System.out.println("BallS");
        }
    }

    @Override
    public void setGameTitle(String title) {
        game.setTitle(ScreenSettings.getName());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 100));
        g2d.drawString("PEENENEI", 40, 40);

        g2d.dispose();
    }
}
