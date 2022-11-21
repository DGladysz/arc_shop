package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, Long> {
    @Query("Select count(entity.id) From AdministrativeUnit entity Where entity.code = ?1")
    Long isExistByCode(String code);

    @Query("Select new com.archiiro.app.Core.Dto.AdministrativeUnitDto(entity, false) From AdministrativeUnit entity Where entity.parent.id is NULL or entity.parent.id = 0 ")
    List<AdministrativeUnitDto> getAllProvince();
}
