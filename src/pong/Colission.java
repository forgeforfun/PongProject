package pong;

import java.awt.*;

/**
 * Created by Elizabeth on 1/5/15.
 */
public class Colission {
    public static boolean isColliding(Point p, PaddlePlayer paddle) {
        return paddle.contains(p);
    }
}
