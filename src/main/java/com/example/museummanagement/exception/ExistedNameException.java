package com.example.museummanagement.exception;

public class ExistedNameException extends Exception{

    public ExistedNameException() {
        super("Name existed!");
    }
}
