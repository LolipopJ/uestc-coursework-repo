package coursework.savetofile;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/30
 */
public class SaveToFile {
    public static void main (String[] args) throws FileNotFoundException {
        //获取文件名
        System.out.print("Input the filename: ");
        Scanner read = new Scanner(System.in);
        String filename = read.nextLine();

        //获取输入内容并保存
        System.out.println("Input the words you want to save to file(':q' to quit):");
        PrintWriter write = new PrintWriter(filename+".txt");
        String line = read.nextLine();

        //输入':q'时结束录入
        while(!":q".equals(line))
        {
            write.println(line);
            line = read.nextLine();
        }

        write.close();
        read.close();

        System.out.println("Successfully save to file!");
    }
}
