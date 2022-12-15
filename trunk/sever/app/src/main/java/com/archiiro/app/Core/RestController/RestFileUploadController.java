package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Domain.Religion;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import com.archiiro.app.Core.Dto.ReligionDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Other.FileUploadUtils;
import com.archiiro.app.Core.Service.AdministrativeUnitService;
import com.archiiro.app.Core.Service.CountryService;
import com.archiiro.app.Core.Service.EthnicsService;
import com.archiiro.app.Core.Service.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/file-upload")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestFileUploadController {
    @Autowired
    private AdministrativeUnitService administrativeUnitService;

    @Autowired
    private EthnicsService ethnicsService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ReligionService religionService;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/administrative-unit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importAdministrativeUnit(@RequestParam("upload") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            List<AdministrativeUnitImportExcel> list = FileUploadUtils.getDataAdministrativeUnit(byteArrayInputStream);
            if(list != null && list.size() > 0) {
                administrativeUnitService.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/ethnics", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importEthnics(@RequestParam("upload") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            List<EthnicsDto> list = FileUploadUtils.getDataEthnics(byteArrayInputStream);
            if(list != null && list.size() > 0) {
                ethnicsService.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/country", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importCountry(@RequestParam("upload") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            List<CountryDto> list = FileUploadUtils.getDataCountry(byteArrayInputStream);
            if(list != null && list.size() > 0) {
                countryService.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/religion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> importReligion(@RequestParam("upload") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            List<ReligionDto> list = FileUploadUtils.getDataReligion(byteArrayInputStream);
            if(list != null && list.size() > 0) {
                religionService.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
