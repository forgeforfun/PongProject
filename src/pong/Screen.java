package pong;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Screen extends JPanel implements Runnable {

    private Thread thread;

    private int FPS = 60;
    private int UPS = 60;

    protected int scoreP1 = 0;
    protected int scoreP2 = 0;

    protected PaddlePlayer player1;
    public Ball ball;

    private boolean running = false;

    public Screen(Frame frame) {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        ball = new Ball(this);
        player1 = new PaddlePlayer();
        running = true;
        Random random = new Random();
        int i = 3;
        boolean reverse = random.nextBoolean();
        System.out.println(reverse);
        if(reverse) {
            ball.xVelocity-=i;
        } else {
            ball.xVelocity+=i;
        }
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                tick();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                revalidate();
                repaint();

                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println(frames);
                FPS = frames;
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    public void tick() {
        ball.tick();
        player1.tick();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setColor(new Color(255, 164, 39));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        String p1 = "" + scoreP1;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString(p1, 170, 64);
        ball.draw(g);
        player1.draw(g);
    }

}
