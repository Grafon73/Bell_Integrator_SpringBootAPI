package ru.bellintegrator.practice.view.info;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Doc
 */
@Getter
@Setter
public class DocView {

    /**
     * Код документа
     */
    @NotNull(message = "Код не может быть пустым")
    private Integer code;

    /**
     * Название документа
     */
    @Size(max = 255)
    @NotEmpty(message = "Название не может быть пустым")
    private String name;


}
