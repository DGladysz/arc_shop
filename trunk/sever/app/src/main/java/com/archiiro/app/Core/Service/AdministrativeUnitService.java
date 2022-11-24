package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministrativeUnitService extends SupportService<AdministrativeUnit, Long> {
    Boolean isExistByCode(String code);
    AdministrativeUnitDto getDtoById(Long id);
    List<AdministrativeUnitDto> getAllProvince();
    List<AdministrativeUnitDto> getChildrenByParentId(Long parentId);
    List<Long> getListChildrenId(Long id);
    AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto dto, Long id);
    void importExcel(List<AdministrativeUnitImportExcel> dtos);
    Integer deleteAdministrative(Long id);
    Page<AdministrativeUnitDto> searchByPage(SearchDto dto);
}
