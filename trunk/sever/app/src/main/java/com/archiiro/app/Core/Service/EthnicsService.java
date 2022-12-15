package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Ethnics;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EthnicsService extends SupportService<Ethnics, Long> {
    List<EthnicsDto> getAllDto();
    Boolean isExistByCode(String code);

    EthnicsDto saveEthnics(EthnicsDto dto, Long id);

    void importExcel(List<EthnicsDto> dtos);

    Boolean deleteEthnics(Long id);

    EthnicsDto getEthnicsDto(Long id);

    Page<EthnicsDto> searchByPage(SearchDto searchDto);


}
