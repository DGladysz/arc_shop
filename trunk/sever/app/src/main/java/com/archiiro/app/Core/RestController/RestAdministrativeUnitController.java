package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Other.FileUploadUtils;
import com.archiiro.app.Core.Service.AdministrativeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/administrative-unit")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestAdministrativeUnitController {
    @Autowired
    private AdministrativeUnitService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdministrativeUnitDto> getDtoById(@PathVariable("id") Long id) {
        AdministrativeUnitDto result = service.getDtoById(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/get-all-province", method = RequestMethod.GET)
    public List<AdministrativeUnitDto> getAllProvince() {
        List<AdministrativeUnitDto> result = service.getAllProvince();
        return result;
    }

    @RequestMapping(value = "/get-children-by-parent/{id}", method = RequestMethod.GET)
    public List<AdministrativeUnitDto> getChildrenByParentId(@PathVariable("id") Long id) {
        List<AdministrativeUnitDto> result = service.getChildrenByParentId(id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public AdministrativeUnitDto saveAdministrativeUnit(@RequestBody AdministrativeUnitDto dto) {
        AdministrativeUnitDto result = service.saveAdministrativeUnit(dto, null);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AdministrativeUnitDto updateAdministrativeUnit(@RequestBody AdministrativeUnitDto dto, @PathVariable("id") Long id) {
        AdministrativeUnitDto result = service.saveAdministrativeUnit(dto, id);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/import-administrative-unit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importExel(@RequestParam("upload") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            List<AdministrativeUnitImportExcel> list = FileUploadUtils.getDataAdministrativeUnit(byteArrayInputStream);
            if(list != null && list.size() > 0) {
                service.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteList(@PathVariable("id") Long id) {
        Integer numberResult = service.deleteAdministrative(id);
        return new ResponseEntity<>("Delete " + numberResult + " record", numberResult > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<AdministrativeUnitDto> searchByPage(@RequestBody SearchDto searchDto) {
        Page<AdministrativeUnitDto> result = service.searchByPage(searchDto);
        return service.searchByPage(searchDto);
    }






}
