package ru.bellintegrator.practice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

/**
 * Юзер
 */
@Entity
@Table(name = "user")
public class User {
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
     * Идентификатор юзера
     */
    @Column(name = "office_id", nullable = false)
    private Integer officeId;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Среднее имя
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Код документа
     */
    @Column(name = "doc_code")
    private Integer docCode;

    /**
     * Имя документа
     */
    @Column(name = "doc_name", length = 50)
    private String docName;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 50)
    private String docNumber;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Код страны
     */
    @Column(name = "citizenship_code")
    private Integer citizenshipCode;

    @Column(name = "citizenship_name")
    private String citizenshipName;

    /**
     * Статус идентификации
     */
    @Column(name = "is_identified",columnDefinition = "TINYINT(1)")
    private Boolean isIdentified;

    @ManyToOne()
    @JoinColumn(name = "office_id",
            insertable=false, updatable=false)
    @JsonIgnore
    private Office office;

    @ManyToOne()
    @JoinColumn(name = "doc_code",
            insertable=false, updatable=false)
    @JsonIgnore
    private Doc doc;

    @ManyToOne()
    @JoinColumn(name = "citizenship_code",
            insertable=false, updatable=false)
    @JsonIgnore
    private Country country;

    /**
     * Конструктор для hibernate
     */
    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getisIdentified() {
        return isIdentified;
    }

    public void setisIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
