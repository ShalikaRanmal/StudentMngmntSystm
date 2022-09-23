package com.models;

import java.util.HashMap;

public class Student extends Entity{
    public Student(){

    }
    public Student(String name, int age, int grade){
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
    private String name;
    private int age;
    private int grade;
    private HashMap<Subject,Integer> marks = new HashMap<Subject,Integer>();


    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    //@Override
    public String getName() {
        return name;
    }
    //@Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public HashMap<Subject, Integer> getMarks() {
        return marks;
    }
    public void setMarks(Subject subject, Integer marks) {
        this.marks.put(subject, marks);
    }

    @Override
    public String toString(){
        return "Student{name = "+name+"|"+" age = "+age+"| Grade = "+grade+"}";
    }

    public boolean isEqualWithName(String name) {
        if(this.name.equals(name)){
            return true;
        }
        return false;
    }

}
