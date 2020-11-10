package ru.bellintegrator.practice.view.office;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс для Office with Filter
 */
@Getter
@Setter
public class OfficeFilterView {

    /**
     * ID Офиса
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