package pong;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Elizabeth on 1/6/15.
 */
public class PauseState {
    public Screen screen;
    public PauseState (Screen screen) {
        this.screen = screen;
    }
    public String[] options = new String[]{"Play", "Restart", "Quit"};

    public int highlighted = 0;

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 194, 29));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        for(int i = 0; i < options.length; i++) {
            if(i == highlighted) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.PLAIN, 72));
            g.drawString(options[i], Game.WIDTH / 2 - 80, 100 + 250 * i);

        }
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_DOWN) {
            highlighted ++;
        }
        if(keyCode == KeyEvent.VK_UP) {
            highlighted--;
        }
        if(keyCode == KeyEvent.VK_ENTER) {
            if(highlighted == 2) {
                System.exit(0);
            }
            if(highlighted == 1) {
                screen.restart();
            }
            if(highlighted == 0) {
                screen.stage = 0;
            }
        }
        if(highlighted > 2) {
            highlighted = 0;
        }
        if(highlighted < 0) {
            highlighted = 2;
        }

    }



}
