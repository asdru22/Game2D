package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;
import java.util.Set;

public class KeyHandler implements KeyListener {

    private final Set<Integer> pressedKeys = new HashSet<>();

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action required for keyTyped in this context
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public boolean isWASDPressed(){
        return pressedKeys.contains(KeyEvent.VK_W) ||
                pressedKeys.contains(KeyEvent.VK_A) ||
                pressedKeys.contains(KeyEvent.VK_S) ||
                pressedKeys.contains(KeyEvent.VK_D);
    }
}

