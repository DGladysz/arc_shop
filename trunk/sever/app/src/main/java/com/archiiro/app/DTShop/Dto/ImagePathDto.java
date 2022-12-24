package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.DTShop.Domain.ImagePath;

public class ImagePathDto {
    private Long id;
    private ProductSaleDto productSale;

    public ImagePathDto() {

    }

    public ImagePathDto(ImagePath entity) {
        if(entity != null) {
            this.id = entity.getId();
            if(entity.getProductSale() != null) {
                this.productSale = new ProductSaleDto(entity.getProductSale(), false); // Không get ảnh
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductSaleDto getProductSale() {
        return productSale;
    }

    public void setProductSale(ProductSaleDto productSale) {
        this.productSale = productSale;
    }
}
