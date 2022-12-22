package com.archiiro.app.DTShop.Domain;

import com.archiiro.app.Core.Domain.BaseObjectMetadata;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_product")
@XmlRootElement
public class Product extends BaseObjectMetadata {
    @Column(name = "voided")
    private Boolean voided; // Xóa fake

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "unit")
    private String unit; // Đơn vị tính

    @Column(name = "status")
    private Integer status;

    @Column(name = "image")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "id_type_product")
    private TypeProduct typeProduct;

    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }
}
