package ru.bellintegrator.practice.office.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

/**
 * Офис
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "office")
public class Office {

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
     * Идентификатор организации
     */
    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    /**
     * Наименование
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * Адрес
     */
    @Column(name = "address", length = 255)
    private String address;


    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Статус офиса
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy="office")
    @JsonIgnore
    private Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id",
            insertable=false, updatable=false)
    @JsonIgnore
    private Organization organization;
}