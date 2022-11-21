package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Domain.Country;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class RestCountryController {
    @Autowired
    private CountryService countryService;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CountryDto> getCountryDto(@PathVariable("id") Long id) {
        CountryDto result = this.countryService.getCountryDto(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CountryDto> saveCountry(@RequestBody CountryDto dto) {
        CountryDto result = this.countryService.saveCountry(dto, null);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto dto, @PathVariable("id") Long id) {
        CountryDto result = this.countryService.saveCountry(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<CountryDto> searchByPage(@RequestBody SearchDto dto) {
        Page<CountryDto> result = this.countryService.searchByPage(dto);
        return result;
    }
}
