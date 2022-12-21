package com.archiiro.app.DTShop.RestController;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.DTShop.Dto.TypeProductDto;
import com.archiiro.app.DTShop.Service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-product")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestTypeProductController {
    @Autowired
    private TypeProductService service;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<TypeProductDto> getAllDto() {
        List<TypeProductDto> result = this.service.getAllDto();
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TypeProductDto getTypeProductDto(@PathVariable("id") Long id) {
        TypeProductDto result = this.service.getDtoById(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public TypeProductDto saveTypeProduct(@RequestBody TypeProductDto dto) {
        TypeProductDto result = this.service.saveDto(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TypeProductDto updateDto(@RequestBody TypeProductDto dto, @PathVariable("id") Long id) {
        TypeProductDto result = this.service.saveDto(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<TypeProductDto> searchByPage(@RequestBody SearchDto dto) {
        Page<TypeProductDto> result = this.service.searchByPage(dto);
        return result;
    }

}
