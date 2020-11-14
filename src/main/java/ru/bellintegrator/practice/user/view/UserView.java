package ru.bellintegrator.practice.user.view;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для User
 */
@Getter
@Setter
public class UserView {

    /**
     * ID юзера
     */
    @NotNull(message = "ID не может быть пустым")
    private Integer id;

    /**
     * Имя
     */
    @Size(max = 50)
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    @NotNull(message = "Фамилия не может быть пустым")
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50)
    @NotNull(message = "Отчесвто не может быть пустым")
    private String middleName;

    /**
     * Должность
     */
    @Size(max = 50)
    @NotNull(message = "Должность не может быть пустым")
    private String position;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Название документа
     */
    @Size(max = 255)
    private String docName;

    /**
     * Номер документа
     */
    @Size(max = 20)
    private String docNumber;

    /**
     * Дата документа
     */
    @Size(max = 20)
    private String docDate;

    /**
     * Название страны
     */
    @Size(max = 50)
    private String citizenshipName;

    /**
     * Код страны
     */
    private Integer citizenshipCode;

    /**
     * Идентификация юзера
     */
    private Boolean isIdentified;

}
