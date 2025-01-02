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
        g2d.setColor(new Color(46, 110, 129));
        g2d.fillRect(0, 0, ScreenSettings.getScreenWidth(), ScreenSettings.getScreenHeight());

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 90f));

        // shadow
        g2d.setColor(Color.BLACK);
        UI.drawCenterText(ScreenSettings.getName(), g2d, -2.9,0.1);

        // main title
        g2d.setColor(Color.WHITE);
        UI.drawCenterText(ScreenSettings.getName(), g2d, -3);
    }
}
