package core;

import ui.UI;

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
    public void loop() {

        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER) && inputDelay == 0) {
            inputDelay = 20;
            changeScene(new CorePanel(game));
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawTitle(g2d);

        g2d.dispose();
    }

    private void drawTitle(Graphics2D g2d) {
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 100f));
        g2d.setColor(Color.WHITE);
        UI.drawCenterText(ScreenSettings.getName(), g2d, -3);
    }
}
