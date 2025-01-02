package ui;

import java.awt.*;

public abstract class Selection {
    private final String name;
    private final int yOffset;
    private final int id;

    public Selection(int id, String name, int yOffset) {
        this.name = name;
        this.yOffset = yOffset;
        this.id = id;
    }

    public abstract void onPress();

    public void draw(Graphics2D g2d, int selection) {
        UI.drawCenterText(name, g2d, yOffset, 0, selection == id);
    }
}
