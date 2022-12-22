package com.archiiro.app.DTShop.Service;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.SupportService;
import com.archiiro.app.DTShop.Domain.Delivery;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeliveryService extends SupportService<Delivery, Long> {
    List<DeliveryDto> getAll();

    Boolean isExist(String code);

    DeliveryDto getDtoById(Long id);

    DeliveryDto saveDelivery(DeliveryDto dto, Long id);

    Boolean deleteDelivery(Long id);

    Page<DeliveryDto> searchByPage(SearchDto searchDto);
}
