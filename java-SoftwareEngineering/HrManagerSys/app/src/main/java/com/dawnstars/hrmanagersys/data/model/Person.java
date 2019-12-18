package com.dawnstars.hrmanagersys.data.model;

/**
 * @author lolipop
 * @version 2019/12/18
 */
public class Person {
    private String name;
    private String sex;

    Person(String readName, String readSex) {
        this.name = readName;
        this.sex = readSex;
    }

    private String getName() {
        return this.name;
    }

    private String getSex() {
        return this.sex;
    }

    private void setName(String writeName) {
        this.name = writeName;
    }

    private void setSex(String writeSex) {
        this.sex = writeSex;
    }
}
