package com.mobilesiri.sqliteexample;

public class Student {

    private int rollno;
    private String name;
    private String studClass;

    public Student()
    {
    }

    public Student(int rollno, String name, String studClass)
    {
        this.rollno = rollno;
        this.name=name;
        this.studClass = studClass;
    }

    public Student(String name, String studClass)
    {
        this.name=name;
        this.studClass = studClass;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    public int getRollno() {

        return rollno;
    }

    public String getStudClass() {
        return studClass;
    }

    public String getName() {
        return name;
    }
}
