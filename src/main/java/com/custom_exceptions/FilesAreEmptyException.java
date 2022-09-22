package com.custom_exceptions;

public class FilesAreEmptyException extends Exception{
    public FilesAreEmptyException(String msg){
        super(msg);
    }
}
