package game.object;

import core.CorePanel;
import io.Sound;
import io.SoundType;

public class Chest extends BaseObject {
    public Chest(int x, int y) {
        super("chest",x, y);
    }

    @Override
    public void onCollision(CorePanel corePanel) {
        corePanel.finishGame();
        Sound.stop(SoundType.MUSIC);
        Sound.play(SoundType.FANFARE);
    }
}
