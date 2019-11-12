package coursework.interfacetest;

interface Print {
    /**
     * print()：打印一些内容
     */
    void print();
}

/**
 * @author Lolipop
 * @lastUpdate 2019/10/28
 */
public class PrintTest {
    public static void main (String[] args) {
        PrintSchool testPrintSchool = new PrintSchool();
        PrintMe testPrintMe = new PrintMe();
        testPrintSchool.print();
        testPrintMe.print();
    }
}

class PrintSchool implements Print {
    @Override
    public void print () {
        System.out.println("Hello, UESTC!");
    }
}

class PrintMe implements Print {
    @Override
    public void print () {
        System.out.println("Lolipop!");
    }
}
