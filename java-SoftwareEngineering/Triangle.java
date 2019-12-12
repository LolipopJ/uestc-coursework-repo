import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/12
 */
public class Triangle {
    private int a, b, c;
    private static Scanner scan = new Scanner(System.in);

    public static void main (String[] args) {
        boolean flag = true;
        int choice;

        while (flag) {
            System.out.print("\n#1 check a triangle.\n#0 quit.\nchoice: #");
            choice = scan.nextInt();
            switch (choice) {
                case 1: checkTriangle(); break;
                case 0: flag = false; System.out.println("You quit successfully."); break;
                default: System.out.println("Wrong code! Check your input.");
            }
        }
    }

    private Triangle(int aLength, int bLength, int cLength) {
        this.a = aLength;
        this.b = bLength;
        this.c = cLength;
    }

    private static void checkTriangle() {
        System.out.print("Length of side a: ");
        int aLength = scan.nextInt();
        System.out.print("Length of side b: ");
        int bLength = scan.nextInt();
        System.out.print("Length of side c: ");
        int cLength = scan.nextInt();

        check(new Triangle(aLength, bLength, cLength));
    }

    private static void check(Triangle triangle) {
        int[] side = {triangle.a, triangle.b, triangle.c};

        // 进行排序，排序后数组值依次增大
        Arrays.sort(side);

        // 边长应大于0
        for (int length : side) {
            if (length <= 0) {
                System.out.println("不能构成三角形（存在边长小于等于0）");
                return;
            }
        }

        // 较小边之和应大于第三边
        if (side[0] + side[1] <= side[2]) {
            System.out.println("不能构成三角形");
            return;
        }

        // 判断三角形类型
        if (side[0] == side[1] || side[1] == side[2]) {
            if (side[0] == side[2]) {
                System.out.println("三角形为等边三角形");
            }
            else {
                System.out.println("三角形为等腰三角形");
            }
        }
        else {
            System.out.println("三角形为一般三角形");
        }
    }
}
