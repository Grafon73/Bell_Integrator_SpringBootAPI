package ru.bellintegrator.practice.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.practice.document.model.Doc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Документ юзера
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User_doc")
public class UserDoc implements Serializable {

    @Id
    private Integer id;

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


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "doc_code", referencedColumnName = "code")
    private Doc doc;


    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
