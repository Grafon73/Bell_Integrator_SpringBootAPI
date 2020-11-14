package ru.bellintegrator.practice.user.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO-класс IN для User with Filter
 */
@Getter
@Setter
public class UserFilterViewIn {

    /**
     * ID офиса юзера
     */
    @NotNull(message = "Office ID не может быть пустым")
    private Integer officeId;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String secondName;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * Должность
     */
    private String position;

    /**
     * Код документа
     */
    private Integer docCode;

    /**
     * Код страны
     */
    private Integer citizenshipCode;

}
