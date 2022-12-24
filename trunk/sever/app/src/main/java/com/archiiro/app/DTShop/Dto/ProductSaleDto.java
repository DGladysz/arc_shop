package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.DTShop.Domain.ImagePath;
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
    private Set<ImagePathDto> imagePaths;

    public ProductSaleDto() {

    }

    public ProductSaleDto(ProductSale entity, boolean arc) {
        if(entity != null) {
            this.id = entity.getId();
            this.startDate = entity.getStartDate();
            this.period = entity.getPeriod();
            this.sale = entity.getSale();
            if(entity.getProduct() != null && entity.getProduct().getId() != null) {
                this.product = new ProductDto(entity.getProduct());
                this.idProduct = entity.getProduct().getId();
            }
            if(arc) {
                if(entity.getImagePaths() != null && entity.getImagePaths().size() > 0) {
                    this.imagePaths = new HashSet<ImagePathDto>();
                    for(ImagePath imagePath : entity.getImagePaths()) {
                        this.imagePaths.add(new ImagePathDto(imagePath));
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

    public Set<ImagePathDto> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(Set<ImagePathDto> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
