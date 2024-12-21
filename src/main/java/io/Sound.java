package io;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

public class Sound {
    private static final Map<SoundType, Clip> soundClips = new EnumMap<>(SoundType.class);

    private static Clip loadClip(SoundType type) throws Exception {
        URL soundUrl = Sound.class.getResource(String.format("/sounds/%s.wav", type.name().toLowerCase()));
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        return clip;
    }

    public static void play(SoundType type) {
        try {
            Clip clip = soundClips.computeIfAbsent(type, t -> {
                try {
                    return loadClip(t);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            if (clip != null) {
                clip.stop(); // Stop any ongoing playback
                clip.setFramePosition(0); // Rewind the clip
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loop(SoundType type) {
        try {
            Clip clip = soundClips.computeIfAbsent(type, t -> {
                try {
                    return loadClip(t);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(SoundType type) {
        Clip clip = soundClips.get(type);
        if (clip != null) {
            clip.stop();
        }
    }

    public static void setVolume(SoundType type, float value) {
        Clip clip = soundClips.get(type);
        if (clip != null) {
            try {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(value); // value is in decibels
            } catch (IllegalArgumentException e) {
                e.printStackTrace(); // Handle cases where the control isn't supported
            }
        }
    }
}

