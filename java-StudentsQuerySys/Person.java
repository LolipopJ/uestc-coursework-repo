import java.io.Serializable;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Person implements Serializable {
    protected String name;
    protected String sex;
    int age;

    Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    String getName() {
        return this.name;
    }

    String getSex() {
        return this.sex;
    }

    String getAge() {
        return Integer.toString(this.age);
    }

    void setName(String name) {
        this.name = name;
    }

    void setSex(String sex) {
        this.sex = sex;
    }

    void setAge(int age) {
        this.age = age;
    }

    void display() {
        System.out.print("name: "+this.name+"\nsex: "+this.sex+"\nage: "+this.age+"\n");
    }
}
