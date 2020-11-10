package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Документ
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "doc")
public class Doc {


    @Id
    @Column(name = "code")
    private int code;

    /**
     * Служебное поле hibernate
     */
    @Version
    @JsonIgnore
    private Integer version;

    /**
     * Название документа
     */
    @Column(name = "name", length = 255)
    private String name;



}


