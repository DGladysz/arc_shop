package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Ethnics;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import org.springframework.data.domain.Page;

public interface EthnicsService extends SupportService<Ethnics, Long> {
    Boolean isExistByCode(String code);

    EthnicsDto saveEthnics(EthnicsDto dto, Long id);

    Boolean deleteEthnics(Long id);

    EthnicsDto getEthnicsDto(Long id);

    Page<EthnicsDto> searchByPage(SearchDto searchDto);
}