package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.TypeProduct;
import com.archiiro.app.DTShop.Dto.TypeProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProduct, Long> {
    @Query("Select new com.archiiro.app.DTShop.Dto.TypeProductDto(entity) From TypeProduct entity")
    List<TypeProductDto> getAllDto();

    @Query("Select count(entity.id) From TypeProduct entity Where entity.code = ?1")
    Long isExistByCode(String code);
    @Query("Select entity From TypeProduct entity Where entity.id = ?1")
    TypeProduct getTypeProduct(Long id);

}
