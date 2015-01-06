package pong;


import javax.swing.*;

public class Game {
    public static final String TITLE = "Homie Pong - by forgeforfun";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;


    public static void main(String[] args) {
        new Game();
    }


    private Game() {
        JFrame frame = new JFrame(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Frame successfuly created!");
        frame.add(new Screen(frame));
    }
}
