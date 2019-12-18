package com.dawnstars.hrmanagersys.data.model;

/**
 * @author lolipop
 * @version 2019/12/18
 */
public class Staff extends Person {
    // 员工号
    private int id;

    // 任职情况
    private String position;

    // 薪资情况
    private int salary;

    Staff(String readName, String readSex, int readId, String readPosition, int readSalary) {
        super(readName, readSex);
        this.id = readId;
        this.position = readPosition;
        this.salary = readSalary;
    }

    private int getId() {
        return this.id;
    }

    private String getPosition() {
        return this.position;
    }

    private int getSalary() {
        return this.salary;
    }

    private void setId(int writeId) {
        this.id = writeId;
    }

    private void setPosition(String writePosition) {
        this.position = writePosition;
    }

    private void setSalary(int writeSalary) {
        this.salary = writeSalary;
    }
}
