package ru.bellintegrator.practice.document.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * DTO-класс для Country
 */
@Getter
@Setter
public class CountryView {
    /**
     * Код страны
     */
    @NotNull(message = "Код не может быть пустым")
    private Integer code;

    /**
     * Название страны
     */
    @Size(max = 50)
    @NotEmpty(message = "Название не может быть пустым")
    private String name;


}
