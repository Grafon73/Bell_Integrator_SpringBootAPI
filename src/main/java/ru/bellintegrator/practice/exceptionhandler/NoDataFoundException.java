package ru.bellintegrator.practice.exceptionhandler;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No data found");
    }
}
