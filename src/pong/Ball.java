package pong;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Elizabeth on 1/5/15.
 */
public class Ball{
    Image img = loadImage("/extra/ball.png");
    public final int ballSpeed = 10;
    private Screen screen;
    private double x;
    private double y;
    private int size = 25;
    private int roundness = 20;
    protected double drag = 0.25;

    protected double xVelocity = 0, yVelocity = 0, maxXVelocity = 12, maxYVelocity = 12;

    public Ball(Screen screen) {
        this.screen = screen;
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;
    }

    public void tick() {
        x += xVelocity;
        y += yVelocity;
        if(xVelocity != 0) {
            if (xVelocity > maxXVelocity) xVelocity = maxXVelocity;
            if (xVelocity < -maxXVelocity) xVelocity = -maxXVelocity;
        }
        if(y < 0) {
            this.yVelocity = 3;
        }
        if(y > Game.HEIGHT - 48) {
            this.yVelocity = -3;
        }
        if(x + 30 < 0) {
            screen.scoreP1 ++;
            x = Game.WIDTH / 2;
            y = Game.HEIGHT / 2;
            Random random = new Random();
            int i = ballSpeed;
            boolean reverse = random.nextBoolean();
            System.out.println(reverse);
            if(reverse) {
                this.xVelocity= -i;
            } else {
                this.xVelocity = i;
            }
            this.yVelocity = random.nextInt(2);
            screen.player1.x = Game.WIDTH - 16;
            screen.player1.y = Game.HEIGHT / 2 - 64 / 2;
            screen.player2.x = 0;
            screen.player2.y = Game.HEIGHT / 2 - 64 / 2;
        }
        if(x > Game.WIDTH) {
            x = Game.WIDTH / 2;
            y = Game.HEIGHT / 2;
            screen.scoreP2 ++;
            Random random = new Random();
            int i = ballSpeed;
            boolean reverse = random.nextBoolean();
            System.out.println(reverse);
            if(reverse) {
                this.xVelocity = -i;
            } else {
                this.xVelocity = i;
            }
            this.yVelocity = random.nextInt(3);
            screen.player1.x = Game.WIDTH - 16;
            screen.player1.y = Game.HEIGHT / 2 - 64 / 2;
            screen.player2.x = 0;
            screen.player2.y = Game.HEIGHT / 2 - 64 / 2;
        }

        if(yVelocity > maxYVelocity)  yVelocity = maxYVelocity;
        if(yVelocity < -maxXVelocity) yVelocity = -maxXVelocity;

        if(Colission.isColliding(new Point((int)x + 16,(int)y - 8), screen.player1) || Colission.isColliding(new Point((int) x, (int) y - 8), screen.player2) || Colission.isColliding(new Point((int)x + 16,(int)y + 8), screen.player1) || Colission.isColliding(new Point((int) x, (int) y + 8), screen.player2)) {
            reverse();
            Random random = new Random();
            //boolean reverse = random.nextBoolean();
            //if(reverse == false) this.yVelocity -= random.nextInt(3);
            //if(reverse == true) this.yVelocity += random.nextInt(3);

            if(xVelocity > 0) {
                if (y > screen.player2.y) {
                    yVelocity = 2;
                } else {
                    yVelocity = -2;
                }
            }

        }

    }

    public void draw(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
    }
    public void reverse() {
        if(xVelocity > 0) {
            xVelocity = -xVelocity;
        } else {
            xVelocity = Math.abs(xVelocity);
        }
    }
    public void respawn() {
        this.x = Game.WIDTH / 2;
        this.y = Game.HEIGHT / 2;
    }
    public BufferedImage loadImage(String fileName) {
        BufferedImage buf = null;
        try{
            buf = ImageIO.read(getClass().getResourceAsStream(fileName));
        }catch(IOException e) {
            e.printStackTrace();
        }
        return buf;
    }
}
