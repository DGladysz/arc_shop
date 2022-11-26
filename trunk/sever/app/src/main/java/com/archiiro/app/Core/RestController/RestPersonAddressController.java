package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Domain.PersonAddress;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.PersonAddressDto;
import com.archiiro.app.Core.Service.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person-address")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestPersonAddressController {
    @Autowired
    private PersonAddressService service;

    @RequestMapping(value = "get-address-by-person/{id}", method = RequestMethod.GET)
    public List<PersonAddressDto> getAllAddress(@PathVariable("id") Long id) {
        List<PersonAddressDto> result = this.service.getAddressByPersonId(id);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public PersonAddressDto saveAddress(@RequestBody PersonAddressDto dto) {
        PersonAddressDto result = this.service.savePersonAddress(dto, null);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PersonAddressDto updateAddress(@RequestBody PersonAddressDto dto, @PathVariable("id") Long id) {
        PersonAddressDto result = this.service.savePersonAddress(dto, id);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonAddressDto getAddressDto(@PathVariable("id") Long id) {
        PersonAddressDto result = this.service.getPersonAddress(id);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteAddress(@PathVariable("id") Long id) {
        Boolean result = this.service.deletePersonAddress(id);
        return result;
    }
}
