package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.Delivery;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query("Select new com.archiiro.app.DTShop.Dto.DeliveryDto(entity) From Delivery entity")
    List<DeliveryDto> getAll();

    @Query("Select count(entity.id) From Delivery entity Where entity.code = ?1")
    Long isExist(String code);
}
