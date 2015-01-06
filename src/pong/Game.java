package pong;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game {
    public static final String TITLE = "Homie Pong - by ForgeForFun";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;


    private BufferedImage icon;
    private Image iconScaled;

    public static void main(String[] args) {
        new Game();
    }


    private Game() {
        try{
            icon = ImageIO.read(this.getClass().getResourceAsStream("/extra/ball.png"));
            iconScaled = icon.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        }catch(IOException e) {
            System.exit(0);
        }
        JFrame frame = new JFrame(TITLE);
        frame.setIconImage(iconScaled);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Frame successfully created!");
        Screen screen = new Screen(frame);
        frame.add(screen);
        frame.addKeyListener(new KeyHandler(screen));
    }

}
