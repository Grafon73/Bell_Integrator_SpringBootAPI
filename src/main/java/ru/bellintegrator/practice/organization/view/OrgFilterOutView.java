package ru.bellintegrator.practice.organization.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для Organization with Filter Respond
 */
@Getter
@Setter
@AllArgsConstructor
public class OrgFilterOutView {

    /**
     * ID Организации
     */
    private int id;

    /**
     * Наименование
     */
    private String name;

    /**
     * Статус организации
     */
    private Boolean isActive;
}