package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Religion;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.ReligionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReligionService extends SupportService<Religion, Long> {
    List<ReligionDto> getAllDto();
    Boolean isExistByCode(String code);

    ReligionDto saveReligion(ReligionDto dto, Long id);

    void importExcel(List<ReligionDto> dtos);

    Boolean deleteReligion(Long id);

    ReligionDto getReligionDto(Long id);

    Page<ReligionDto> searchByPage(SearchDto searchDto);
}
