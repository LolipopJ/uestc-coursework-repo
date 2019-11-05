package coursework.classarray;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/25
 */
public class TestArray {
    public static void main (String[] args) {
        ArraySort array = new ArraySort();

        //字符串数组赋值并传递给array数组
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the unsorted numbers(a blank to divide): ");
        String[] nums = scanner.nextLine().split(" ");
        int size = nums.length;
        array.sim = new int[size];
        for (int i=0; i<nums.length; i++){
            array.sim[i] = Integer.parseInt(nums[i]);
        }

        //调用setOrder方法并打印结果
        array.setOrder();
        System.out.print("Sorted nums: ");
        for (int i=0; i<nums.length; i++){
            System.out.printf("%d ", array.sim[i]);
        }
    }
}

class ArraySort {
    int[] sim;
    void setOrder() {
        Arrays.sort(sim);
    }
}
