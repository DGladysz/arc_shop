package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Religion;
import com.archiiro.app.Core.Dto.ReligionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {
    @Query("Select new com.archiiro.app.Core.Dto.ReligionDto(entity) From Religion entity")
    List<ReligionDto> getAllDto();
    @Query("Select count(entity.id) From Religion entity Where entity.code = ?1")
    Long isExistByCode(String code);

    @Query("Select entity From Religion entity Where entity.id = ?1")
    Religion getById(Long id);
}
