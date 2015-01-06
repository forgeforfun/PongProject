package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Elizabeth on 1/5/15.
 */
public class KeyHandler implements KeyListener {
    private Screen screen;
    public KeyHandler(Screen screen) {
        this.screen = screen;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        screen.player1.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        screen.player1.keyReleased(e.getKeyCode());
    }
}
