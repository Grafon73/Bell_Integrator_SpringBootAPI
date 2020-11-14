package ru.bellintegrator.practice.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bellintegrator.practice.user.model.User;

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
@Data
@NoArgsConstructor
@Table(name = "User_doc")
public class UserDoc implements Serializable {

    @Id
    @Column(name = "id")
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

    /**
     * Код документа
     */
    @Column(name = "doc_code", length = 50)
    private Integer docCode;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "doc_code", referencedColumnName = "code",
           insertable=false, updatable=false)
    private Doc doc;

   @OneToOne(fetch = FetchType.LAZY)
   @MapsId()
   @JoinColumn(name = "id")
   private User user;


}
