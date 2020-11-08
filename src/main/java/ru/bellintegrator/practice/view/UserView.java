package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для User
 */
public class UserView {

    /**
     * ID юзера
     */
    @NotNull(message = "ID не может быть пустым")
    private int id;

    /**
     * Имя
     */
    @Size(max = 50)
    @NotNull(message = "Имя не может быть пустым")
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    @NotNull(message = "Фамилия не может быть пустым")
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50)
    @NotNull(message = "Отчесвто не может быть пустым")
    private String middleName;

    /**
     * Должность
     */
    @Size(max = 50)
    @NotNull(message = "Должность не может быть пустым")
    private String position;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Название документа
     */
    @Size(max = 255)
    private String docName;

    /**
     * Номер документа
     */
    @Size(max = 20)
    private String docNumber;

    /**
     * Дата документа
     */
    @Size(max = 20)
    private String docDate;

    /**
     * Название страны
     */
    @Size(max = 50)
    private String citizenshipName;

    /**
     * Код страны
     */
    private Integer citizenshipCode;

    /**
     * Идентификация юзера
     */
    private Boolean isIdentified;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
    }

}
