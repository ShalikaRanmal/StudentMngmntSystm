package com.main_classes;

public class Subject {
    public Subject(){

    }
    public Subject(String name){
        this.subject_name=name;
    }
    private String subject_name;

    public String getSubject_name() {
        return subject_name;
    }
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String toString(){
        return subject_name;
    }
    public boolean isEqualWithName(String name) {
        if(this.subject_name.equals(name)){
            return true;
        }
        return false;
    }

}
