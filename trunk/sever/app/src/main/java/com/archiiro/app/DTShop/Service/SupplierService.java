package com.archiiro.app.DTShop.Service;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.SupportService;
import com.archiiro.app.DTShop.Domain.Supplier;
import com.archiiro.app.DTShop.Dto.SupplierDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SupplierService extends SupportService<Supplier, Long> {
    List<SupplierDto> getAll();

    Boolean isExist(String code);

    SupplierDto getDtoById(Long id);

    SupplierDto saveSupplier(SupplierDto dto, Long id);

    Boolean deleteSupplier(Long id);

    Page<SupplierDto> searchByPage(SearchDto searchDto);
}
