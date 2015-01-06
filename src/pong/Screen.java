package pong;


import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel implements Runnable{

    private Thread thread;

    private boolean running = false;

    public Screen(Frame frame) {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        running = true;

        while(running) {
            tick();
        }
    }

    public void tick() {

    }


}
