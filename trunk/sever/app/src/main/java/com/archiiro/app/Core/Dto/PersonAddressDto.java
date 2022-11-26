package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.PersonAddress;

public class PersonAddressDto {
    private Long id;
    private String address;
    private String city;
    private String province;
    private String country;
    private Long idPerson;

    public PersonAddressDto() {

    }

    public PersonAddressDto(PersonAddress entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.address = entity.getAddress();
            this.city = entity.getCity();
            this.province = entity.getProvince();
            this.country = entity.getCountry();
            if(entity.getPerson() != null && entity.getPerson().getId() != null) {
                this.idPerson = entity.getPerson().getId();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }
}
