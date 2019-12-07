package coursework;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/30
 */
public class PillarTest {
    public static void main (String[] args) {
        Pillar pi1 = new Pillar();
        pi1.setPillar(3, 10, 15, 20);
        System.out.print("the pillar's volume: "+pi1.getVolume()+"\n");

        Pillar pi2 = new Pillar();
        pi2.setPillar(4, 20, 5, 10);
        System.out.print("the pillar's volume: "+pi2.getVolume()+"\n");
    }
}

class Shape {
    private int n;
    private double area;
    private double length;
    private double width;

    Shape () {
        n = 0;
        area = 0;
        length = 0;
        width = 0;
    }

    void setData (int sides, double l, double w) {
        this.n = sides;
        this.length = l;
        this.width = w;
    }

    static class Triangle {
        /**
         * 计算三角形时，length为底边长，width为底边上的高
         */
        double getTriangleArea (double l, double w) {
            return l*w/2;
        }
    }

    static class Rectangle {
        /**
         * 计算矩形时，length为长，width为宽
         */
        double getRectangleArea (double l, double w) {
            return l*w;
        }
    }

    double getArea () {
        int sides = this.n;
        switch (sides) {
            case 3: Triangle tr = new Triangle(); this.area = tr.getTriangleArea(this.length, this.width); break;
            case 4: Rectangle re = new Rectangle(); this.area = re.getRectangleArea(this.length, this.width); break;
            default: System.out.println("Wrong sides number!");
        }
        return this.area;
    }
}

class Pillar {
    private double height;
    private double volume;
    private Shape bottom = new Shape();

    Pillar () {
        height = 0;
        volume = 0;
    }

    void setPillar (int sides, double l, double w, int h) {
        bottom.setData(sides, l, w);
        this.height = h;
    }

    double getVolume () {
        this.volume = bottom.getArea() * this.height;
        return volume;
    }
}
