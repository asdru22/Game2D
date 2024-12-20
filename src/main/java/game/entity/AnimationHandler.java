package game.entity;

import io.IOUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationHandler {
    public enum Animations {
        WalkingUp, WalkingDown, WalkingLeft, WalkingRight
    }

    private final Map<Animations, Animation> animations;
    private final Entity entity;
    private Animation currentAnimation;
    private int frameCounter;
    private final int MAX_FRAMES = 12;

    public static class Animation {
        private final ArrayList<BufferedImage> frames;
        private int index;

        public Animation(ArrayList<BufferedImage> frames) {
            this.frames = frames;
            index = 0;
        }

        public void update() {
            index = (index + 1) % frames.size();
        }

        public BufferedImage getCurrentFrame() {
            return frames.get(index);
        }
    }

    public AnimationHandler(Entity e) {
        animations = new HashMap<>();
        this.entity = e;
    }

    public void setCurrent(Animations key) {
        this.currentAnimation = animations.get(key);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void add(Animations key, String name, int size) {
        ArrayList<BufferedImage> frames = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            String path = String.format("entity/%s/%s_%s", entity.getId(), name, i);
            frames.add(IOUtils.loadImage(path));

        }

        animations.put(key, new Animation(frames));

    }

    public void update() {
        frameCounter++;
        if (frameCounter > MAX_FRAMES) {
            frameCounter = 0;
            this.currentAnimation.update();
        }
    }

}
