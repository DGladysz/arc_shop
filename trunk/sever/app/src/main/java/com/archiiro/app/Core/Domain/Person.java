package com.archiiro.app.Core.Domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_person")
@XmlRootElement
public class Person extends BaseObject{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_number_issue_by")
    private String idNumberIssueBy;

    @Column(name = "id_number_issue_date")
    private Date idNumberIssueDate;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = true)
    protected Country nationality; // Quốc tịch

    @ManyToOne
    @JoinColumn(name = "native_village", nullable = true)
    protected AdministrativeUnit nativeVillage; // Quê quán

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "ethnics_id", nullable = true)
    protected Ethnics ethnics; // Dân tộc

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "religion_id", nullable = true)
    protected Religion religion; // Tôn giáo

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    protected Set<PersonAddress> address; // Địa chỉ

//    @ManyToOne(cascade = {CascadeType.ALL}, optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", unique = false)
//    protected User user;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumberIssueBy() {
        return idNumberIssueBy;
    }

    public void setIdNumberIssueBy(String idNumberIssueBy) {
        this.idNumberIssueBy = idNumberIssueBy;
    }

    public Date getIdNumberIssueDate() {
        return idNumberIssueDate;
    }

    public void setIdNumberIssueDate(Date idNumberIssueDate) {
        this.idNumberIssueDate = idNumberIssueDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public AdministrativeUnit getNativeVillage() {
        return nativeVillage;
    }

    public void setNativeVillage(AdministrativeUnit nativeVillage) {
        this.nativeVillage = nativeVillage;
    }

    public Ethnics getEthnics() {
        return ethnics;
    }

    public void setEthnics(Ethnics ethnics) {
        this.ethnics = ethnics;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Set<PersonAddress> getAddress() {
        return address;
    }

    public void setAddress(Set<PersonAddress> address) {
        this.address = address;
    }
}
