package coursework.throwable;

import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/11/6
 */
public class TryTest {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("1. AException\n2. AIOOBException\n3. NPException\nInput the choice: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                new AException();
                break;
            case 2:
                new AIOOBException();
                break;
            case 3:
                new NPException();
                break;
            default:
                System.out.println("Wrong code!");
        }
    }
}

/**
 * ArithmeticException: 算术错误情形
 */
class AException {
    AException () {
        try {
            int a = 3;
            a = a / 0;
        } catch (ArithmeticException e) {
            System.err.println("Error message: "+e.getMessage());
            System.err.println("Exception string:"+e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("------------------\nGoodbye!");
        }
    }
}

/**
 * ArrayIndexOutOfBoundsException: 数组大小小于或大于实际的数组大小
 */
class AIOOBException {
    AIOOBException () {
        try {
            int[] a = new int[2];
            a[4] = 3;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error message: "+e.getMessage());
            System.err.println("Exception string:"+e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("------------------\nGoodbye!");
        }
    }
}

/**
 * NullPointerException: 尝试访问null对象成员
 */
class NPException {
    NPException () {
        try {
            String name = null;
            if (name.equals("null")){
                System.out.print(name);
            }
        } catch (NullPointerException e) {
            System.err.println("Error message: "+e.getMessage());
            System.err.println("Exception string:"+e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("------------------\nGoodbye!");
        }
    }
}
