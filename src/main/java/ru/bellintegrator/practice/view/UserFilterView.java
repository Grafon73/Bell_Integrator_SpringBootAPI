package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для User with Filter
 */
public class UserFilterView {
    /**
     * ID юзера
     */
    @NotNull(message = "ID не может быть пустым")
    private int id;

    /**
     * Имя
     */
    @Size(max = 50)
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    @NotNull(message = "Фамилия не может быть пустой")
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50)
    @NotNull(message = "Отчество не может быть пустым")
    private String middleName;

    /**
     * Должность
     */
    @Size(max = 50)
    @NotNull(message = "Должность не может быть пустая")
    private String position;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

}
