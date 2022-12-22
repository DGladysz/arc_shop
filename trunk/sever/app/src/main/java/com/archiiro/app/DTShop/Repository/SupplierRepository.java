package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.Supplier;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import com.archiiro.app.DTShop.Dto.SupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("Select new com.archiiro.app.DTShop.Dto.SupplierDto(entity) From Supplier entity")
    List<SupplierDto> getAll();

    @Query("Select count(entity.id) From Supplier entity Where entity.code = ?1")
    Long isExist(String code);

}
