package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Office
 */
public class OfficeView {

    /**
     * ID Офиса
     */
    @NotNull(message = "ID не может быть пустым")
    private int id;


    /**
     * Наименование
     */
    @Size(max = 50)
    @NotNull(message = "Название не может быть пустым")
    private String name;

    /**
     * Адрес
     */
    @Size(max = 255)
    @NotNull(message = "Адрес не может быть пустым")
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Статус организации
     */
    private Boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getisActive() {
        return isActive;
    }

    public void setisActive(Boolean active) {
        isActive = active;
    }
}