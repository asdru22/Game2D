package game.object;

import core.CorePanel;
import game.entity.Player;
import io.Sound;
import io.SoundType;

public class Key extends BaseObject {
    public Key(int x, int y) {
        super("key", x, y);
    }

    @Override
    public void onCollision(CorePanel corePanel) {
        Sound.play(SoundType.COIN);
        Player p = corePanel.getGameObjects().getPlayer();
        p.increaseKeys();
        corePanel.getGameObjects().getObjects().remove(this);

        corePanel.getGameUI().showMessage("You got a key!");
    }
}
