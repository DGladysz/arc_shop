package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.ProductSale;
import com.archiiro.app.DTShop.Dto.ProductSaleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("Select new com.archiiro.app.DTShop.Dto.ProductSaleDto(entity, true) From ProductSale entity")
    List<ProductSaleDto> getAll();
}
