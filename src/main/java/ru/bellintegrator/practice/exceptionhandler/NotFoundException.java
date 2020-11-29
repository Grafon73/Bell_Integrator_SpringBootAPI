package ru.bellintegrator.practice.exceptionhandler;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String name, Integer id) {
        super(String.format("%s with Id %d not found", name, id));
    }
    public NotFoundException(String name) {
        super(String.format("%s not found", name));
    }
}