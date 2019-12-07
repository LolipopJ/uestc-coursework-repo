/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestPerson {
    public static void main(String[] args) {
        Person testPerson = new Person("XiaoMing", "Male", 20);
        testPerson.display();
        System.out.println("-----------------");

        testPerson.setName("XiaoHong");
        testPerson.display();
        System.out.println("-----------------");

        testPerson.setSex("Female");
        testPerson.display();
        System.out.println("-----------------");

        testPerson.setAge(18);
        testPerson.display();
        System.out.println("-----------------");

        String info = "name: "+testPerson.getName()+"\nsex: "+testPerson.getSex()+"\nage: "+testPerson.getAge();
        System.out.print(info);
    }
}
