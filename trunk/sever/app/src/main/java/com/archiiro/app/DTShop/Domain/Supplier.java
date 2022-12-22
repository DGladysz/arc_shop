package com.archiiro.app.DTShop.Domain;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Domain.BaseObjectMetadata;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_supplier")
@XmlRootElement
public class Supplier extends BaseObjectMetadata {
    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnit address;

    @Column(name = "address_detail")
    private String addressDetails;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AdministrativeUnit getAddress() {
        return address;
    }

    public void setAddress(AdministrativeUnit address) {
        this.address = address;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }
}
