package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Ethnics;
import com.archiiro.app.Core.Dto.EthnicsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EthnicsRepository extends JpaRepository<Ethnics, Long> {
    @Query("Select new com.archiiro.app.Core.Dto.EthnicsDto(entity) From Ethnics entity")
    List<EthnicsDto> getAllDto();
    @Query("Select count(entity.id) From Ethnics entity Where entity.code = ?1")
    Long isExistByCode(String code);

    @Query("Select entity From Ethnics entity Where entity.id = ?1")
    Ethnics getById(Long id);
}
