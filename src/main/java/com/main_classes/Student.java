package com.main_classes;

import com.main_classes.Subject;

import java.util.HashMap;

public class Student {
    public Student(){

    }
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
    private HashMap<Subject,Integer> marks = new HashMap<Subject,Integer>();


    public String getName() {
        return name;
    }
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
        return "Student{name="+name+"|"+"age="+age+"}";
    }

    public boolean isEqualWithName(String name) {
        if(this.name.equals(name)){
            return true;
        }
        return false;
    }

}
