package com.archiiro.app.DTShop.Service;

import com.archiiro.app.Core.Service.SupportService;
import com.archiiro.app.DTShop.Domain.ProductSale;
import com.archiiro.app.DTShop.Dto.ProductSaleDto;

import java.util.Date;
import java.util.List;

public interface ProductSaleService extends SupportService<ProductSale, Long> {
    List<ProductSaleDto> getAllDto();

    ProductSaleDto saveProductSale(ProductSaleDto dto, Long id);

    ProductSaleDto getProductSale(Long id);

    Boolean deleteProductSale(Long id);

    void LockSale(Date startDate, Integer period, Date currentDate);
}
