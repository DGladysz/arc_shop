package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Domain.PersonAddress;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersonDto {
    private Long id;
    private Long userId;
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
    private CountryDto nationality;
    private AdministrativeUnitDto nativeVillage;
    private EthnicsDto ethnics;
    private ReligionDto religion;
    private Set<PersonAddressDto> address;

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

    public PersonDto(Person entity, boolean arc) {
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
            if(arc == true) {
                if(entity.getUser() != null) {
                    this.userId = entity.getUser().getId();
                }
                if(entity.getNationality() != null) {
                    this.nationality = new CountryDto(entity.getNationality());
                }
                if(entity.getNativeVillage() != null) {
                    this.nativeVillage = new AdministrativeUnitDto(entity.getNativeVillage());
                }
                if(entity.getEthnics() != null) {
                    this.ethnics = new EthnicsDto(entity.getEthnics());
                }
                if(entity.getReligion() != null) {
                    this.religion = new ReligionDto(entity.getReligion());
                }
                if(entity.getAddress() != null) {
                    Set<PersonAddressDto> address = new HashSet<>();
                    Iterator iterator = entity.getAddress().iterator();
                    while (iterator.hasNext()) {
                        PersonAddress personAddress = (PersonAddress) iterator.next();
                        PersonAddressDto personAddressDto = new PersonAddressDto();
                        personAddressDto.setId(personAddress.getId());
                        personAddressDto.setAddress(personAddress.getAddress());
                        personAddressDto.setCity(personAddress.getCity());
                        personAddressDto.setProvince(personAddress.getProvince());
                        personAddressDto.setCountry(personAddress.getCountry());
                        personAddressDto.setIdPerson(entity.getId());
                        address.add(personAddressDto);
                    }
                    this.address = address;
                }
            }
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

    public CountryDto getNationality() {
        return nationality;
    }

    public void setNationality(CountryDto nationality) {
        this.nationality = nationality;
    }

    public AdministrativeUnitDto getNativeVillage() {
        return nativeVillage;
    }

    public void setNativeVillage(AdministrativeUnitDto nativeVillage) {
        this.nativeVillage = nativeVillage;
    }

    public EthnicsDto getEthnics() {
        return ethnics;
    }

    public void setEthnics(EthnicsDto ethnics) {
        this.ethnics = ethnics;
    }

    public ReligionDto getReligion() {
        return religion;
    }

    public void setReligion(ReligionDto religion) {
        this.religion = religion;
    }

    public Set<PersonAddressDto> getAddress() {
        return address;
    }

    public void setAddress(Set<PersonAddressDto> address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
