package com.archiiro.app.DTShop.RestController;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import com.archiiro.app.DTShop.Dto.TypeProductDto;
import com.archiiro.app.DTShop.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestDeliveryController {
    @Autowired
    private DeliveryService service;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<DeliveryDto> getAllDto() {
        List<DeliveryDto> result = this.service.getAll();
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DeliveryDto getDeliveryDto(@PathVariable("id") Long id) {
        DeliveryDto result = this.service.getDtoById(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public DeliveryDto saveDelivery(@RequestBody DeliveryDto dto) {
        DeliveryDto result = this.service.saveDelivery(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DeliveryDto updateDto(@RequestBody DeliveryDto dto, @PathVariable("id") Long id) {
        DeliveryDto result = this.service.saveDelivery(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<DeliveryDto> searchByPage(@RequestBody SearchDto dto) {
        Page<DeliveryDto> result = this.service.searchByPage(dto);
        return result;
    }
}
