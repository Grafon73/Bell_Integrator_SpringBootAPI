package ru.bellintegrator.practice.exception;

public class InvalidInputData extends RuntimeException{
    public InvalidInputData(String name,String param) {
        super(String.format("Invalid %s param for %s Entity", param,name));
    }
}

