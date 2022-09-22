package com.custom_exceptions;

public class NoSuchSubjectException extends Exception{
    public NoSuchSubjectException(String msg){
        super(msg);
    }
}
