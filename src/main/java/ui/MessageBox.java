package ui;

import core.CorePanel;
import core.impl.GameState;
import core.impl.ScreenSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessageBox {

    private final CorePanel corePanel;

    private final int
            size = ScreenSettings.getTileSize(),
            x = size * 2,
            y = size / 2,
            width = ScreenSettings.getScreenWidth() - size * 4,
            height = size * 4,
            textX = x + size,
            textY = y + size;

    private final ArrayList<String> messages = new ArrayList<>();
    private int messageIndex = 0;

    public MessageBox(CorePanel corePanel) {
        this.corePanel = corePanel;
    }

    public void draw(Graphics2D g2d) {
        // box
        g2d.setColor(new Color(0, 0, 0, 210));
        g2d.fillRoundRect(x, y, width, height, 10, 10);

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(x, y, width, height, 10, 10);

        // message
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20f));
        FontMetrics metrics = g2d.getFontMetrics();
        int maxWidth = width - size * 2;
        String[] words = messages.get(messageIndex).split(" ");
        StringBuilder line = new StringBuilder();
        int lineY = textY;

        for (String word : words) {
            String testLine = line + word + " ";
            if (metrics.stringWidth(testLine) > maxWidth) {
                g2d.drawString(line.toString(), textX, lineY);
                line = new StringBuilder(word + " ");
                lineY += metrics.getHeight();
            } else {
                line.append(word).append(" ");
            }
        }
        g2d.drawString(line.toString(), textX, lineY);
    }

    public void addMessages(String[] newMessages) {
        messages.addAll(List.of(newMessages));
    }

    public void nextMessage() {
        messageIndex++;
        if(messageIndex >= messages.size()){
            reset();
        }
    }

    private void reset(){
        messageIndex = 0;
        messages.clear();
        corePanel.setGameState(GameState.PLAYING);
    }
}
