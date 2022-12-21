package com.archiiro.app.DTShop.Service;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.SupportService;
import com.archiiro.app.DTShop.Domain.TypeProduct;
import com.archiiro.app.DTShop.Dto.TypeProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TypeProductService extends SupportService<TypeProduct, Long> {
    List<TypeProductDto> getAllDto();
    Boolean isExistByCode(String code);
    TypeProductDto saveDto(TypeProductDto dto, Long id);

    TypeProductDto getDtoById(Long id);

    Boolean deleteTypeProduct(Long id);

    Page<TypeProductDto> searchByPage(SearchDto searchDto);
}
