package pong;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Screen extends JPanel implements Runnable {

    private Thread thread;

    public PauseState state;

    private int FPS = 60;
    private int UPS = 60;

    protected boolean winner;

    protected int stage = 0;

    protected int scoreP1 = 0;
    protected int scoreP2 = 0;

    protected PaddlePlayer player1;
    protected PaddlePlayer1 player2;
    public Ball ball;

    private boolean running = false;

    public Screen(Frame frame) {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        state = new PauseState(this);
        ball = new Ball(this);
        player1 = new PaddlePlayer();
        player2 = new PaddlePlayer1();
        running = true;
        Random random = new Random();
        int i = ball.ballSpeed;
        boolean reverse = random.nextBoolean();
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
                FPS = frames;
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    public void tick() {
        if(stage == 0) {
            ball.tick();
            player2.tick();
            player1.tick();
            if(scoreP1 >= 5) {
                winner = true;
                stage = 1;
            }
            if(scoreP2 >= 5) {
                winner = false;
                stage = 1;
            }
        }
        if(stage == 2) {
            state.tick();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setColor(new Color(117, 214, 255));
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        String p1 = "" + scoreP1;
        String p2 = "" + scoreP2;
        g.setColor(Color.BLACK);
        if(stage == 0) {
            g.setFont(new Font("Arial", Font.PLAIN, 72));
            g.drawString(p2, 256, 64);
            g.drawString(p1, Game.WIDTH - 256, 64);
            g.setFont(new Font("Arial", Font.PLAIN, 12));

            ball.draw(g);
            player2.draw(g);
            player1.draw(g);
        } else if(stage == 1) {
            g.setFont(new Font("Arial", Font.PLAIN, 32));
            g.setColor(new Color(255, 66, 217));
            g.fillRect(Game.WIDTH / 2 - 256 / 2, Game.HEIGHT / 2 - 128, 256, 128);
            g.setColor(Color.BLACK);
                if (winner) {
                    g.drawString("Winner: player 2", Game.WIDTH / 2 - 120, Game.HEIGHT / 2 - 48);
                } else {
                    g.drawString("Winner: player 1", Game.WIDTH / 2 - 120 , Game.HEIGHT / 2 - 48);
                }
            g.drawString("Press R to restart", Game.WIDTH / 2 - g.getFont().getSize() * 9, Game.HEIGHT - g.getFont().getSize());
        } else if(stage == 2) {
            state.draw(g);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("fps:" + FPS, Game.WIDTH / 2 - getFont().getSize() * 3, 10);
    }

    public void restart() {
        player1 = new PaddlePlayer();
        player2 = new PaddlePlayer1();
        ball.respawn();
        ball.xVelocity = new Random().nextBoolean() ? ball.ballSpeed : -ball.ballSpeed;
        ball.yVelocity = new Random().nextInt(3);
        scoreP1 = 0;
        scoreP2 = 0;
        stage = 0;
    }
}
