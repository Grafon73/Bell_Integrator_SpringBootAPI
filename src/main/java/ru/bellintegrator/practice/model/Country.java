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
 * Страна
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "country")
public class Country{

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
     * Название страны
     */
    @Column(name = "name", length = 50)
    private String name;

}
