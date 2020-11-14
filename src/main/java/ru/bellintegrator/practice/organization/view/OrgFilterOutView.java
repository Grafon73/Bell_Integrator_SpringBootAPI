package ru.bellintegrator.practice.organization.view;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для Organization with Filter Respond
 */
@Getter
@Setter
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