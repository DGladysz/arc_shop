package com.archiiro.app.DTShop.RestController;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.DTShop.Domain.Supplier;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import com.archiiro.app.DTShop.Dto.SupplierDto;
import com.archiiro.app.DTShop.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestSupplierController {
    @Autowired
    private SupplierService service;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<SupplierDto> getAllDto() {
        List<SupplierDto> result = this.service.getAll();
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SupplierDto getSupplierDto(@PathVariable("id") Long id) {
        SupplierDto result = this.service.getDtoById(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public SupplierDto saveSupplier(@RequestBody SupplierDto dto) {
        SupplierDto result = this.service.saveSupplier(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SupplierDto updateDto(@RequestBody SupplierDto dto, @PathVariable("id") Long id) {
        SupplierDto result = this.service.saveSupplier(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteSupplier(@PathVariable("id") Long id) {
        Boolean result = this.service.deleteSupplier(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<SupplierDto> searchByPage(@RequestBody SearchDto dto) {
        Page<SupplierDto> result = this.service.searchByPage(dto);
        return result;
    }
}
