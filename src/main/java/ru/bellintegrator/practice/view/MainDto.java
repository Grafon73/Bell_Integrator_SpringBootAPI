package ru.bellintegrator.practice.view;

public class MainDto {


    private Object data;

    public MainDto(Object object) {
        this.data = object;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
