package coursework;

import java.util.Scanner;
import java.util.Calendar;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/25
 */
public class MyDate {
    public static void main (String[] args) {
        Date date = new Date();
        date.initDate();

        Scanner scan = new Scanner(System.in);
        int choice, addYear, addMonth, addDay;

        do {
            System.out.print("\n1: reset date\n2: add date\n3: show date\n0: exit\ninput choice: ");
            choice = scan.nextInt();
            switch (choice){
                case 1: date.initDate(); date.printDate(); break;
                case 2:
                    System.out.print("add year: ");
                    addYear = scan.nextInt();
                    System.out.print("add month: ");
                    addMonth = scan.nextInt();
                    System.out.print("add day: ");
                    addDay = scan.nextInt();
                    date.addDate(addYear, addMonth, addDay);
                    date.printDate();
                    break;
                case 3: date.printDate(); break;
                case 0: break;
                default: System.out.println("Wrong code!");
            }
        } while (choice != 0);

        System.out.println("You quit successfully.");
    }
}

class Date {
    private int year;
    private int day;
    private int month;
    private Calendar cal = Calendar.getInstance();
    private Calendar calAdded = Calendar.getInstance();

    void initDate(){
        year = cal.get(Calendar.YEAR);
        //第一个月的值为0，故应在月份上加一以表示客观的月份
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DATE);
        calAdded = Calendar.getInstance();
    }

    void addDate(int y, int m, int d){
        calAdded.add(Calendar.YEAR, y);
        calAdded.add(Calendar.MONTH, m);
        calAdded.add(Calendar.DATE, d);

        year = calAdded.get(Calendar.YEAR);
        month = calAdded.get(Calendar.MONTH)+1;
        day = calAdded.get(Calendar.DATE);
    }

    void printDate(){
        System.out.print("当前状态日期: "+year+"-"+month+"-"+day+"\n");
    }
}