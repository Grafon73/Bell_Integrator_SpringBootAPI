package ru.bellintegrator.practice.organization.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO-класс для Organization with Filter Request
 */
@Getter
@Setter
@AllArgsConstructor
public class OrgFilterIn {

     /**
     * Наименование
     */
    private String name;

    /**
     * ИНН
     */
    private String inn;

    /**
     * Статус организации
     */
    private Boolean isActive;
}