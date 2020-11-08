package ru.bellintegrator.practice.view;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Organization
 */
public class OrgView {
    /**
     * ID Организации
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
     * Полное наименование
     */
    @Size(max = 255)
    @NotNull(message = "Полное название не может быть пустым")
    private String fullName;

    /**
     * ИНН
     */
    @Size(max = 50)
    @NotNull(message = "ИНН не может быть пустым")
    private String inn;

    /**
     * КПП
     */
    @Size(max = 50)
    @NotNull(message = "КПП не может быть пустым")
    private String kpp;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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
