package coursework;

import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/25
 */
public class TestPoint {
    public static void main (String[] args) {
        Point point = new Point();
        System.out.print("Init point. Now, ");
        point.getPoint();

        point.setX(0);
        point.setY(0);
        System.out.print("Set start position. Now, ");
        point.getPoint();

        point.movePoint(10, 20);
        System.out.print("Move point. Now, ");
        point.getPoint();
    }
}

class Point {
    private int x;
    private int y;
    Point () {
        x = 2019;
        y = 1025;
    }
    void setX(int positionX) {
        x = positionX;
    }
    void setY(int positionY) {
        y = positionY;
    }
    void getPoint() {
        System.out.printf("point position: (%d,%d)\n", x, y);
    }
    void movePoint(int moveX, int moveY) {
        x += moveX;
        y += moveY;
    }
}
