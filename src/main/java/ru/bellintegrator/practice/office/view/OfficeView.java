package ru.bellintegrator.practice.office.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Office
 */
@Getter
@Setter
@AllArgsConstructor
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
}