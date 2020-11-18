package ru.bellintegrator.practice.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Документ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "doc")
public class Doc {

    @Id
    @Column(name = "code")
    private Integer code;

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


