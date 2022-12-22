package com.archiiro.app.DTShop.RestController;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import com.archiiro.app.DTShop.Dto.ProductDto;
import com.archiiro.app.DTShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestProductController {
    @Autowired
    private ProductService service;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<ProductDto> getAllDto() {
        List<ProductDto> result = this.service.getAll();
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDto getProductDto(@PathVariable("id") Long id) {
        ProductDto result = this.service.getDtoById(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ProductDto saveProduct(@RequestBody ProductDto dto) {
        ProductDto result = this.service.saveProduct(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(@RequestBody ProductDto dto, @PathVariable("id") Long id) {
        ProductDto result = this.service.saveProduct(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteProduct(@PathVariable("id") Long id) {
        Boolean result = this.service.deleteProduct(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/delete-voided/{id}", method = RequestMethod.DELETE)
    public Boolean deleteVoided(@PathVariable("id") Long id) {
        Boolean result = this.service.deleteVoided(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<ProductDto> searchByPage(@RequestBody SearchDto dto) {
        Page<ProductDto> result = this.service.searchByPage(dto);
        return result;
    }

}
