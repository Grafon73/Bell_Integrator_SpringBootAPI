package ru.bellintegrator.practice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

/**
 * Организация
 */
@Entity
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
    private Integer version;

    /**
     * Наименование
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", length = 50, nullable = false)
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
    @Column(name = "address", length = 50, nullable = false)
    private String address;


    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Статус организации
     */
    @Column(name = "is_active",columnDefinition = "TINYINT(1)")
    private Boolean isActive;


    @OneToMany(mappedBy="organization")
    @JsonIgnore
    private Set<Office> offices;

    /**
     * Конструктор для hibernate
     */
    public Organization(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getisActive() {
        return isActive;
    }

    public void setisActive(Boolean active) {
        isActive = active;
    }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public void addOffice(Office office) {
        getOffices().add(office);
    }

    public void removeOffice(Office office) {
        getOffices().remove(office);
    }
}
