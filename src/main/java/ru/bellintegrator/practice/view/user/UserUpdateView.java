package ru.bellintegrator.practice.view.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для AddUser
 */
@Getter
@Setter
public class UserUpdateView {

    /**
     * ID юзера
     */
    @NotNull(message = "ID не может быть пустым")
    private Integer id;

    /**
     * ID офиса юзера
     */
    private Integer officeId;

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
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50)
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
     * Код документа
     */
    private Integer docCode;

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
     * Код страны
     */
    private Integer citizenshipCode;

    /**
     * Идентификация юзера
     */
    private Boolean isIdentified;

}