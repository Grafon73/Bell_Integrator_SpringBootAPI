package ru.bellintegrator.practice.user.view;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO-класс OUT для User with Filter
 */
@Getter
@Setter
public class UserFilterViewOut {
    /**
     * ID юзера
     */
    private Integer id;

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
}
