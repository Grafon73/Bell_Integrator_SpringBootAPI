package ru.bellintegrator.practice.organization.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;
import java.util.Set;

/**
 * Организация
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(kpp, that.kpp) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(offices, that.offices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, inn, kpp, address, phone, isActive, offices);
    }
}
