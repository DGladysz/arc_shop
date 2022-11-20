package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.Person;

import java.util.Date;

public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String displayName;
    private Date birthDate;
    private String birthPlace;
    private Boolean gender;
    private String email;
    private String phoneNumber;
    private String idNumber;
    private String idNumberIssueBy;
    private Date idNumberIssueDate;
    private String imagePath;

    public PersonDto() {

    }

    public PersonDto(Person entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.firstName = entity.getFirstName();
            this.lastName = entity.getLastName();
            this.displayName = entity.getDisplayName();
            this.birthDate = entity.getBirthDate();
            this.birthPlace = entity.getBirthPlace();
            this.gender = entity.getGender();
            this.email = entity.getEmail();
            this.phoneNumber = entity.getPhoneNumber();
            this.idNumber = entity.getIdNumber();
            this.idNumberIssueBy = entity.getIdNumberIssueBy();
            this.idNumberIssueDate = entity.getIdNumberIssueDate();
            this.imagePath = entity.getImagePath();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
