package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Country;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService extends SupportService<Country, Long> {
    List<CountryDto> getAllDto();
    Boolean isExistByCode(String code);

    CountryDto saveCountry(CountryDto dto, Long id);

    void importExcel(List<CountryDto> dtos);

    Boolean deleteCountry(Long id);

    CountryDto getCountryDto(Long id);

    Page<CountryDto> searchByPage(SearchDto searchDto);
}
