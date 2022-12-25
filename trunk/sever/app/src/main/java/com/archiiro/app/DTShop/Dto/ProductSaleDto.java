package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.DTShop.Domain.Image;
import com.archiiro.app.DTShop.Domain.ProductSale;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductSaleDto {
    private Long id;
    private Date startDate;
    private Integer period;
    private Double sale;
    private ProductDto product;
    private Long idProduct;
    private Set<ImageDto> images;

    public ProductSaleDto() {

    }
    public ProductSaleDto(ProductSale entity, boolean includeImage) {
        if(entity != null) {
            this.id = entity.getId();
            this.startDate = entity.getStartDate();
            this.period = entity.getPeriod();
            this.sale = entity.getSale();
            if(entity.getProduct() != null && entity.getProduct().getId() != null) {
                this.product = new ProductDto(entity.getProduct(), false);
                this.idProduct = entity.getProduct().getId();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }
}
