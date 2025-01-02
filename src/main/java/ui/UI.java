package ui;

import core.CorePanel;
import core.ScreenSettings;

import java.awt.*;

public class UI {
    private final CorePanel corePanel;
    private final Font ARIAL_40, ARIAL_60;

    private boolean messageOn = false;
    private String message;
    private int messageCounter = 0;

    public UI(CorePanel corePanel) {
        this.corePanel = corePanel;
        this.ARIAL_40 = new Font("Arial", Font.PLAIN, 40);
        this.ARIAL_60 = new Font("Arial", Font.BOLD, 60);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void stopMessage() {
        message = null;
        messageOn = false;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(this.ARIAL_40);
        g2d.setColor(Color.WHITE);

        final int tileSize = ScreenSettings.getTileSize();

        switch (corePanel.getGameState()) {
            case PLAYING -> playing(g2d);
            case PAUSED -> paused(g2d);
        }

    }

    private void playing(Graphics2D g2d) {

    }

    private void paused(Graphics2D g2d) {
        drawCenterText("PAUSED", g2d, -3);
    }

    public static void drawCenterText(String text, Graphics2D g2d, double offsetY, double offsetX) {
        int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        final int size = ScreenSettings.getTileSize();
        int x = ScreenSettings.getScreenWidth() / 2 - textLength / 2;
        int y = (int) (ScreenSettings.getScreenHeight() / 2.0 + offsetY * size);
        g2d.drawString(text, (int) (x + offsetX * size), y);

    }

    public static void drawCenterText(String text, Graphics2D g2d, double offsetY) {
        drawCenterText(text, g2d, offsetY, 0);
    }

}
