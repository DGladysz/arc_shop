package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestCountryController {
    @Autowired
    private CountryService countryService;
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<CountryDto> getAllDto() {
        List<CountryDto> result = this.countryService.getAllDto();
        return result;
    }
    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CountryDto getCountryDto(@PathVariable("id") Long id) {
        CountryDto result = this.countryService.getCountryDto(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CountryDto saveCountry(@RequestBody CountryDto dto) {
        CountryDto result = this.countryService.saveCountry(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CountryDto updateCountry(@RequestBody CountryDto dto, @PathVariable("id") Long id) {
        CountryDto result = this.countryService.saveCountry(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<CountryDto> searchByPage(@RequestBody SearchDto dto) {
        Page<CountryDto> result = this.countryService.searchByPage(dto);
        return result;
    }
}
