package coursework;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/24
 */
public class TestArray {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //将输入的数字建立字符串数组
        System.out.print("Input the unsorted numbers(a blank to divide): ");
        String[] nums = scanner.nextLine().split(" ");
        int size = nums.length;

        //将字符串数组转化为int型赋给sim数组
        int[] sim = new int[size];
        for (int i=0; i<nums.length; i++){
            sim[i] = Integer.parseInt(nums[i]);
        }

        //排序sim数组并输出
        Arrays.sort(sim);
        System.out.print("数字从小到大序列为: ");
        for(int num: sim){
            System.out.printf("%d ", num);
        }
    }
}
