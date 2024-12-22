package game.object;

import core.CorePanel;
import game.entity.Player;
import io.Sound;
import io.SoundType;

public class Boots extends BaseObject {
    public Boots(int x, int y) {
        super("boots", x, y);
    }

    @Override
    public void onCollision(CorePanel corePanel) {
        Player player = corePanel.getGameObjects().getPlayer();
        Sound.play(SoundType.POWERUP);
        player.addSpeed(2);
        corePanel.getGameObjects().getObjects().remove(this);
        corePanel.getGameUI().showMessage("Speed up");
    }
}
