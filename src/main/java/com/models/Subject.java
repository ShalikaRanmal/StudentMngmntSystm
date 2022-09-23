package com.models;

public class Subject extends Entity{
    public Subject(){

    }
    public Subject(String name){
        this.name=name;
    }
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    public boolean isEqualWithName(String name) {
        if(this.name.equals(name)){
            return true;
        }
        return false;
    }

}
