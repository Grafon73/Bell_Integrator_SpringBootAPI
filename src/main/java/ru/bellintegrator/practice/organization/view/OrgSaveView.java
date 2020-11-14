package ru.bellintegrator.practice.organization.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Save Organization
 */
@Getter
@Setter
public class OrgSaveView {

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

}
