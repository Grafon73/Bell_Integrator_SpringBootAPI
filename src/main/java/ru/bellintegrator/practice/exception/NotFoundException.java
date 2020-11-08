package ru.bellintegrator.practice.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String name, Integer id) {
        super(String.format("%s with Id %d not found", name, id));
    }

    public NotFoundException(String name, String param) {
        super(String.format("%s with param %s not found", name, param));
    }
    public NotFoundException(String name) {
        super(String.format("%s not found", name));
    }
}