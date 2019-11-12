package coursework;

import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/23
 */
public class NumberSum {
    public static void main(String[] args){
        int total = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number: ");
        long num = scanner.nextLong();
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        System.out.printf("The sum of every single number is: %d\n", total);
    }
}
