package pong;

import java.awt.*;
import java.awt.event.KeyEvent;


public class PaddlePlayer extends Rectangle{


    protected int paddleWidth = 16;
    protected int paddleHeight = 64;

    protected boolean upPressed = false;
    protected boolean downPressed = false;
    //protected double yVelocity, maxYVelocity;

    protected int moveSpeed = 6;
    public PaddlePlayer () {
        this.setBounds(Game.WIDTH - paddleWidth, Game.HEIGHT / 2 - paddleHeight / 2 , paddleWidth, paddleHeight);
        this.x=Game.WIDTH - paddleWidth;
        this.y = Game.HEIGHT / 2 - paddleHeight / 2;
    }

    public void tick() {
            if (upPressed) y-= moveSpeed;
            if (downPressed) y+= moveSpeed;
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
        if(keyCode == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    public void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }






}
