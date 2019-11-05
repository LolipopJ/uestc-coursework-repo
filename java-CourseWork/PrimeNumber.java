package coursework;

import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/25
 */
public class PrimeNumber {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the max number: ");
        int n = scanner.nextInt();
        int[] number = new int[n+1];
        for (int i=1; i<number.length; i++) {
            //默认赋值为0
            number[i] = 0;

            //非质数赋值为1
            if (i>3) {
                for (int count = 2; count<=(i/2); count++) {
                    if (i%count == 0) {
                        number[i] = 1;
                        break;
                    }
                }
            }

            System.out.printf("number[%d]=%d\n", i, number[i]);
        }
    }
}
