package ru.bellintegrator.practice.auxiliaryclasses;

public class MainDtoView {

    private Object data;

    public MainDtoView(Object object) {
        this.data = object;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
