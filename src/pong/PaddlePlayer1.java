package pong;

import java.awt.event.KeyEvent;

/**
 * Created by Elizabeth on 1/6/15.
 */
public class PaddlePlayer1 extends PaddlePlayer {

    public PaddlePlayer1 () {
        setBounds(0, Game.HEIGHT / 2 - paddleHeight / 2, paddleWidth, paddleHeight);
    }


    @Override
    public void keyPressed(int keyCode) {

        if(keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_W) {
            upPressed = true;
        }

    }

    @Override
    public void keyReleased(int keyCode) {

        if(keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }
    }
}
