package ru.bellintegrator.practice.office.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO-класс для Save Office
 */
@Getter
@Setter
@AllArgsConstructor
public class OfficeSaveView {

    /**
     * ID Офиса
     */
    @NotNull(message = "ID организации не может быть пустым")
    private int orgId;

    /**
     * Наименование
     */
    private String name;

    /**
     * Адрес
     */
    private String address;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Статус организации
     */
    private Boolean isActive;
}