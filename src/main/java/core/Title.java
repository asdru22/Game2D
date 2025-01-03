package core;

import io.IOUtils;
import ui.Selection;
import ui.UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Title extends GamePanel {

    private final int
            size = ScreenSettings.getTileSize(),
            iconScale = 2,
            x = ScreenSettings.getScreenWidth() / 2 - (size * iconScale) / 2,
            y = ScreenSettings.getScreenHeight() / 2 - size;

    private final BufferedImage icon = IOUtils.loadScaledImage("entity/player/walking/down_1");

    private final Selection[] selections = new Selection[]{
            new Selection(0, "NEW GAME", 3) {
                @Override
                public void onPress() {
                    newGame();
                }
            },
            new Selection(1, "LOAD GAME", 4) {
                @Override
                public void onPress() {
                    loadGame();
                }
            },
            new Selection(2, "QUIT", 5) {
                @Override
                public void onPress() {
                    quit();
                }
            }
    };

    private int selectionIndex = 0;

    public Title(Game game) {
        super(game);
    }

    @Override
    protected void init() {
        this.keyHandler = game.getKeyHandler();
    }

    @Override
    public void loop() {

        if (keyHandler.isKeyPressed(KeyEvent.VK_W) && inputDelay == 0) {
            inputDelay = 10;
            selectUp();
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_S) && inputDelay == 0) {
            inputDelay = 10;
            selectDown();
        }

        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER) && inputDelay == 0) {
            inputDelay = 20;
            selections[selectionIndex].onPress();
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
        UI.drawCenterText(ScreenSettings.getName(), g2d, -2.9, 0.1);

        // main title
        g2d.setColor(Color.WHITE);
        UI.drawCenterText(ScreenSettings.getName(), g2d, -3);

        // icon
        g2d.drawImage(icon, x, y, size * iconScale, size * iconScale, null);

        // menu
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 48f));

        for (Selection selection : selections) {
            selection.draw(g2d, selectionIndex);
        }
    }

    private void selectUp() {
        selectionIndex--;
        if (selectionIndex < 0) selectionIndex = selections.length - 1;
    }

    private void selectDown() {
        selectionIndex++;
        selectionIndex %= selections.length;
    }

    private void newGame() {
        changeScene(new CorePanel(game));
    }

    private void loadGame() {
        System.out.println("Load Game");
    }

    private void quit() {
        System.exit(0);
    }
}
