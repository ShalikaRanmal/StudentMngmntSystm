package com.models;

public class Entity {
    public String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isEqualWithName(String name) {
        if(this.name.equals(name)){
            return true;
        }
        return false;
    }


}
