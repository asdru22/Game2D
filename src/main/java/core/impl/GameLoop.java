package core.impl;

public interface GameLoop extends Runnable {

    @Override
    default void run() {
        double drawInterval = 10e8 / ScreenSettings.getTargetFPS(),
                delta = 0;
        long lastTime = System.nanoTime(),
                currentTime, timer = 0,
                timeDelta;
        byte fps = 0;

        while (canRun()) {
            currentTime = System.nanoTime();
            timeDelta = currentTime - lastTime;
            delta += timeDelta / drawInterval;
            timer += timeDelta;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                fps++;
            }

            if (timer >= 10e8) {
                setGameTitle(String.format("%s (%dFPS)", ScreenSettings.getName(), fps));
                fps = 0;
                timer = 0;
            }
        }
    }

    boolean canRun();
    void update();
    void repaint();
    void setGameTitle(String title);
}
