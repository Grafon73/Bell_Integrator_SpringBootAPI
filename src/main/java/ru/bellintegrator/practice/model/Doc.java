package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Документ
 */
@Entity
@Table(name = "doc")
public class Doc implements Serializable {


    @Id
    @Column(name = "code")
    private int code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название документа
     */
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy="doc")
    @JsonIgnore
    private Set<User> users;

    /**
     * Конструктор для hibernate
     */
    public Doc(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}


