package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Religion;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.ReligionDto;
import org.springframework.data.domain.Page;

public interface ReligionService extends SupportService<Religion, Long> {
    Boolean isExistByCode(String code);

    ReligionDto saveReligion(ReligionDto dto, Long id);

    Boolean deleteReligion(Long id);

    ReligionDto getReligionDto(Long id);

    Page<ReligionDto> searchByPage(SearchDto searchDto);
}
