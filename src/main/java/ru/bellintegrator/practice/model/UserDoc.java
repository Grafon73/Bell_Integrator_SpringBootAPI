package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Документ юзера
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "User_doc")
public class UserDoc {

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
     * Номер документа
     */
    @Column(name = "doc_number", length = 20)
    private String docNumber;

    /**
     * Дата документа
     */
    @Column(name = "doc_date", length = 20)
    private String docDate;

    /**
     * Код документа
     */
    @Column(name = "doc_code", length = 50)
    private Integer docCode;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_code",
            insertable=false, updatable=false)
    private Doc doc;


}
