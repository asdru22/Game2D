package ui;

import core.CorePanel;
import core.impl.ScreenSettings;

import java.awt.*;

public class UI {
    private final CorePanel corePanel;
    private final Font ARIAL_40, ARIAL_60;
    private static final BasicStroke wideStroke = new BasicStroke(2);

    private boolean messageOn = false;
    private String message;
    private int messageCounter = 0;

    private final Health health = new Health();

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
        health.draw(g2d, corePanel.getPlayer());
    }

    private void paused(Graphics2D g2d) {
        drawCenterText("PAUSED", g2d, -3);
    }

    public static void drawCenterText(String text, Graphics2D g2d,
                                      double offsetY, double offsetX, boolean underline) {
        int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        final int size = ScreenSettings.getTileSize();
        int x = ScreenSettings.getScreenWidth() / 2 - textLength / 2;
        int y = (int) (ScreenSettings.getScreenHeight() / 2.0 + offsetY * size);
        g2d.drawString(text, (int) (x + offsetX * size), y);

        if (underline) {
            int underlineY = y + 5;
            g2d.setStroke(wideStroke);
            g2d.drawLine((int) (x + offsetX * size), underlineY, (int) (x + offsetX * size) + textLength, underlineY);
        }
    }

    public static void drawCenterText(String text, Graphics2D g2d, double offsetY) {
        drawCenterText(text, g2d, offsetY, 0, false);
    }

    public static void drawCenterText(String text, Graphics2D g2d, double offsetY, double offsetX) {
        drawCenterText(text, g2d, offsetY, offsetX, false);
    }

    public static void drawUnderlinedCenterText(String text, Graphics2D g2d, double offsetY) {
        drawCenterText(text, g2d, offsetY, 0, true);
    }
}
