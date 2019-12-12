import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/12
 */
public class Grade {
    public static void main (String[] args) {
        float[] readGrades = new float[50];
        float[] validGrades;
        float sum;
        int count;
        Scanner scan = new Scanner(System.in);

        // 读取学生成绩
        System.out.println("Input students' grades(a blank to divide)");
        for (count = 0; count < readGrades.length; count++) {
            float grade = scan.nextFloat();

            if (grade != -1) {
                readGrades[count] = grade;
            } else {
                break;
            }
        }

        // 将读取的学生成绩复制到等大的数组中
        float[] grades = new float[count];
        System.arraycopy(readGrades, 0, grades, 0, count);

        // 获取有效成绩存储到validGrades数组中
        validGrades = getValidGrades(grades);

        // 若不存在有效成绩（数组为空），则退出程序
        if (validGrades == null) {
            System.out.println("未输入有效成绩！");
            return;
        }

        // 计算有效成绩总分
        sum = getGradesSum(validGrades);

        // 打印出相关内容
        System.out.println("有效成绩数: "+validGrades.length);
        System.out.println("有效成绩总分: "+sum);
        System.out.println("有效成绩均分: "+(sum/validGrades.length));
    }

    public static float[] getValidGrades (float[] grades) {
        int count = 0;

        // 计算有效成绩数并将有效成绩前移到对应位置
        for (float grade : grades) {
            if (grade < 0 || grade > 100) {
                continue;
            }
            grades[count] = grade;
            count++;
        }

        // 若存在有效成绩，则将有效成绩复制到等大数组
        // 返回该数组
        if (count != 0) {
            float[] validGrades = new float[count];
            System.arraycopy(grades, 0, validGrades, 0, count);
            return validGrades;
        }

        // 若不存在有效成绩，返回null
        else {
            return null;
        }
    }

    public static float getGradesSum (float[] grades) {
        float sum = 0;
        for (float grade : grades) {
            sum += grade;
        }
        return sum;
    }
}
