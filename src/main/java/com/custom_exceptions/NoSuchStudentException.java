package com.custom_exceptions;

public class NoSuchStudentException extends Exception {
    public NoSuchStudentException(String msg){
        super(msg);
    }
}
