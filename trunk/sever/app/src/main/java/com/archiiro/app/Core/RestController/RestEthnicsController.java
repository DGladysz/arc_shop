package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.EthnicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ethnics")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestEthnicsController {
    @Autowired
    private EthnicsService service;
    @RequestMapping(value = "get-all", method = RequestMethod.GET)
    public List<EthnicsDto> getAllDto() {
        List<EthnicsDto> result = this.service.getAllDto();
        return result;
    }
    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EthnicsDto> getEthnicsDto(@PathVariable("id") Long id) {
        EthnicsDto result = this.service.getEthnicsDto(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<EthnicsDto> saveEthnics(@RequestBody EthnicsDto dto) {
        EthnicsDto result = this.service.saveEthnics(dto, null);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EthnicsDto> updateEthnics(@RequestBody EthnicsDto dto, @PathVariable("id") Long id) {
        EthnicsDto result = this.service.saveEthnics(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
        public Page<EthnicsDto> searchByPage(@RequestBody SearchDto dto) {
        Page<EthnicsDto> result = this.service.searchByPage(dto);
        return result;
    }
}
