package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.DTShop.Domain.Delivery;

public class DeliveryDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer status;
    private AdministrativeUnitDto address;
    private Long countryId;
    private String countryCode;
    private String countryName;
    private Long districtId;
    private String districtCode;
    private String districtName;
    private Long cityId;
    private String cityCode;
    private String cityName;
    private String addressDetail;

    public DeliveryDto() {

    }

    public DeliveryDto(Delivery entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.status = entity.getStatus();
            if(entity.getAddress() != null) {
                this.address = new AdministrativeUnitDto(entity.getAddress(), true);
                this.countryId = entity.getAddress().getId();
                this.countryCode = entity.getAddress().getCode();
                this.countryName = entity.getAddress().getName();
                if(entity.getAddress().getParent() != null) {
                    this.districtId = entity.getAddress().getParent().getId();
                    this.districtCode = entity.getAddress().getParent().getCode();
                    this.districtName = entity.getAddress().getParent().getName();
                }
                if(entity.getAddress().getParent().getParent() != null) {
                    this.cityId = entity.getAddress().getParent().getParent().getId();
                    this.cityCode = entity.getAddress().getParent().getParent().getCode();
                    this.cityName = entity.getAddress().getParent().getParent().getName();
                }
            }
            this.addressDetail = entity.getAddressDetails();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AdministrativeUnitDto getAddress() {
        return address;
    }

    public void setAddress(AdministrativeUnitDto address) {
        this.address = address;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
