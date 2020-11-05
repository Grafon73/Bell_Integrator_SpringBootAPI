package ru.bellintegrator.practice.view;


import ru.bellintegrator.practice.model.Office;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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

    private Set<Office> offices;
}
