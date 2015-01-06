package pong;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Elizabeth on 1/5/15.
 */
public class PaddlePlayer extends Rectangle{


    private int paddleWidth = 16;
    private int paddleHeight = 64;

    protected boolean wPressed = false;
    protected boolean sPressed = false;
    //protected double yVelocity, maxYVelocity;

    protected int moveSpeed = 2;
    public PaddlePlayer () {
        this.setBounds(Game.WIDTH - paddleWidth, Game.HEIGHT / 2 - paddleHeight / 2 , paddleWidth, paddleHeight);
    }

    public void tick() {
            if (wPressed) y-= moveSpeed;
            if (sPressed) y+= moveSpeed;
        if(y <= 0) {
            y = 0;
        } else if(y > Game.HEIGHT - paddleHeight) {
            y = Game.HEIGHT - paddleHeight - 1;
        }


    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_S) {
            sPressed = true;
        }
        if(keyCode == KeyEvent.VK_W) {
            wPressed = true;
        }
    }

    public void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_S) {
            sPressed = false;
        }
        if(keyCode == KeyEvent.VK_W) {
            wPressed = false;
        }
    }





}
