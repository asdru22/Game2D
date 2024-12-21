package game;

import core.CorePanel;
import core.PanelSettings;
import io.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    private final CorePanel corePanel;
    private final Font text, congratulations;
    private final BufferedImage keyImage;

    private boolean messageOn = false;
    private String message;
    private int messageCounter = 0;

    private double playTime = 0;

    private final int
            SCREEN_X = 74,
            SCREEN_Y = 65;

    public UI(CorePanel corePanel) {
        this.corePanel = corePanel;
        this.text = new Font("Arial", Font.PLAIN, 40);
        this.congratulations = new Font("Arial", Font.BOLD, 60);

        this.keyImage = IOUtils.loadImage("object/key");
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
        g2d.setFont(this.text);
        g2d.setColor(Color.WHITE);
        final int tileSize = PanelSettings.getTileSize();

        if (corePanel.isGameFinished()) {

            drawCenterText("You found the treasure!", g2d,
                    PanelSettings.getTileSize() * -3);

            drawCenterText(String.format("Time: %.2f seconds", playTime), g2d,
                    PanelSettings.getTileSize() * -2);

            g2d.setFont(this.congratulations);
            g2d.setColor(Color.RED);
            drawCenterText("Congratulations!", g2d, PanelSettings.getTileSize() * 2);

            return;
        }

        // keys
        g2d.drawImage(keyImage, tileSize / 2, tileSize / 2, tileSize, tileSize, null);
        g2d.drawString(String.format("x %s", corePanel.getPlayer().getKeys()),
                SCREEN_X, SCREEN_Y);


        // time
        playTime += (double) 1 / PanelSettings.getTargetFPS();
        g2d.drawString(String.format("Time: %.2f", playTime), tileSize * 11, 65);

        // message
        if (messageOn) {
            g2d.setFont(g2d.getFont().deriveFont(20F));
            g2d.drawString(message, tileSize / 2, tileSize * 5);
            messageCounter++;
            if (messageCounter > 90) {
                stopMessage();
            }
        }
    }

    private void drawCenterText(String text, Graphics2D g2d, int offsetY) {
        int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = PanelSettings.getScreenWidth() / 2 - textLength / 2;
        int y = PanelSettings.getScreenHeight() / 2 + offsetY;
        g2d.drawString(text, x, y);

    }
}
