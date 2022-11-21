package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.Core.Dto.SearchDto;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministrativeUnitService extends SupportService<AdministrativeUnit, Long> {
    Boolean isExistByCode(String code);
    AdministrativeUnitDto getDtoById(Long id);
    List<AdministrativeUnitDto> getAllProvince();
    List<AdministrativeUnitDto> getListWard(Long administrativeId);
    List<AdministrativeUnitDto> getAllByParentId(Long parentId);
    List<Long> getListId(Long id);
    AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto dto, Long id);
    Integer deleteAdministrative(Long id);
    Page<AdministrativeUnitDto> searchByPage(SearchDto dto);
}
