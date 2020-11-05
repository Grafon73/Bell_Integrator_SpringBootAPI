package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для Doc
 */
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
