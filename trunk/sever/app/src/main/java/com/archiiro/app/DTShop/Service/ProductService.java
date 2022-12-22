package com.archiiro.app.DTShop.Service;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.SupportService;
import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends SupportService<Product, Long> {
    List<ProductDto> getAll();

    Boolean isExist(String code, String name);

    ProductDto getDtoById(Long id);

    ProductDto saveProduct(ProductDto dto, Long id);

    Boolean deleteProduct(Long id);

    Boolean deleteVoided(Long id);

    Page<ProductDto> searchByPage(SearchDto searchDto);
}
