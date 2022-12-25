package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.DTShop.Domain.Image;
import com.archiiro.app.DTShop.Domain.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean voided;
    private Double price;
    private Long quantity;
    private String unit;
    private Integer status;
    private TypeProductDto typeProduct;
    private Long idTypeProduct;
    private Set<ImageDto> images;

    public ProductDto() {

    }
    public ProductDto(Product entity, boolean includeImage) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.voided = entity.getVoided();
            this.price = entity.getPrice();
            this.quantity = entity.getQuantity();
            this.unit = entity.getUnit();
            this.status = entity.getStatus();
            if(entity.getTypeProduct() != null && entity.getTypeProduct().getId() != null) {
                this.typeProduct = new TypeProductDto(entity.getTypeProduct());
                this.idTypeProduct = entity.getTypeProduct().getId();
            }
            if(includeImage) {
                if(entity.getImages() != null && entity.getImages().size() > 0) {
                    this.images = new HashSet<ImageDto>();
                    for(Image image : entity.getImages()) {
                        this.images.add(new ImageDto(image, false));
                    }
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

    public TypeProductDto getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProductDto typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Long getIdTypeProduct() {
        return idTypeProduct;
    }

    public void setIdTypeProduct(Long idTypeProduct) {
        this.idTypeProduct = idTypeProduct;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }
}
