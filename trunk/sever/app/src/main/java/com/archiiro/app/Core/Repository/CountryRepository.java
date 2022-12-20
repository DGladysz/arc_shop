package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Country;
import com.archiiro.app.Core.Dto.CountryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("Select new com.archiiro.app.Core.Dto.CountryDto(entity) From Country entity")
    List<CountryDto> getAllDto();
    @Query("Select count(entity.id) From Country entity Where entity.code = ?1")
    Long isExistByCode(String code);
    @Query("Select entity From Country entity Where entity.id = ?1")
    Country getById(Long id);
}
