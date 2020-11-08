package ru.bellintegrator.practice.exception;

public class InvalidInputData extends RuntimeException{

    public InvalidInputData(String name) {
        super(String.format("Invalid params for %s Entity", name));
    }
    public InvalidInputData(String name,String param) {
        super(String.format("Invalid %s param for %s Entity", param,name));
    }
}

