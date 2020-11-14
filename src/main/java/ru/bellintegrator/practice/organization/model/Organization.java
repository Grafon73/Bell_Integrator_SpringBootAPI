package ru.bellintegrator.practice.organization.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

/**
 * Организация
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Служебное поле hibernate
     */
    @Version
    @JsonIgnore
    private Integer version;

    /**
     * Наименование
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", length = 50, nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", length = 50, nullable = false)
    private String kpp;

    /**
     * Адрес
     */
    @Column(name = "address", length = 255, nullable = false)
    private String address;


    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Статус организации
     */
    @Column(name = "is_active")
    private Boolean isActive;


    @OneToMany(mappedBy="organization")
    @JsonIgnore
    private Set<Office> offices;

}
