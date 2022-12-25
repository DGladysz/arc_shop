package com.archiiro.app.DTShop.Dto;

import com.archiiro.app.DTShop.Domain.Image;

public class ImageDto {
    private Long id;
    private String name;
    private ProductDto product;
    private ProductSaleDto productSale;

    public ImageDto() {

    }

    public ImageDto(Image entity, boolean includeOther) {
        if(entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            if(includeOther) {
                if(entity.getProduct() != null) {
                    this.product = new ProductDto(entity.getProduct(), false);
                }
                if(entity.getProductSale() != null) {
                    this.productSale = new ProductSaleDto(entity.getProductSale(), false);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public ProductSaleDto getProductSale() {
        return productSale;
    }

    public void setProductSale(ProductSaleDto productSale) {
        this.productSale = productSale;
    }
}
