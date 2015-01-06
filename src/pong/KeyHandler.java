package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(screen.stage != 2) {
                screen.stage = 2;
                screen.state.highlighted = 0;
            } else {
                screen.stage = 0;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_R) {
            screen.restart();
        } else {
            if (screen.stage == 0) {
                screen.player1.keyPressed(e.getKeyCode());
                screen.player2.keyPressed(e.getKeyCode());
            }
        }
        if(screen.stage == 2) {
            screen.state.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

            if (screen.stage == 0) {
                screen.player1.keyReleased(e.getKeyCode());
                screen.player2.keyReleased(e.getKeyCode());
            }

    }
}
