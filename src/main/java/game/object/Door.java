package game.object;

import core.CorePanel;
import game.entity.Player;
import io.Sound;
import io.SoundType;

public class Door extends BaseObject {
    public Door(int x, int y) {
        super("door", x, y);
        collision = true;
    }

    @Override
    public void onCollision(CorePanel corePanel) {
        Player p = corePanel.getGameObjects().getPlayer();
        if (p.getKeys() >= 1) {
            Sound.play(SoundType.UNLOCK);
            corePanel.getGameObjects().getObjects().remove(this);
            p.decreaseKeys();
            corePanel.getGameUI().showMessage("You opened the door");
        } else {
            corePanel.getGameUI().showMessage("You need a key");
        }
    }
}
