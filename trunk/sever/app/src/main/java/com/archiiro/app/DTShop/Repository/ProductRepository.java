package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select new com.archiiro.app.DTShop.Dto.ProductDto(entity) From Product entity")
    List<ProductDto> getAll();

    @Query("Select count(entity.id) From Product entity Where entity.code = ?1 Or entity.name = ?2")
    Long isExistCodeOrName(String code, String name);
}
