package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Country;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.SearchDto;
import org.springframework.data.domain.Page;

public interface CountryService extends SupportService<Country, Long> {
    Boolean isExistByCode(String code);

    CountryDto saveCountry(CountryDto dto, Long id);

    Boolean deleteCountry(Long id);

    CountryDto getCountryDto(Long id);

    Page<CountryDto> searchByPage(SearchDto searchDto);
}
