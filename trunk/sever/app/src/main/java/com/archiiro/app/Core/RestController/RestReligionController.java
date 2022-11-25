package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.ReligionDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/religion")
public class RestReligionController {
    @Autowired
    private ReligionService service;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReligionDto> getReligionDto(@PathVariable("id") Long id) {
        ReligionDto result = this.service.getReligionDto(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ReligionDto> saveReligion(@RequestBody ReligionDto dto) {
        ReligionDto result = this.service.saveReligion(dto, null);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ReligionDto> updateReligion(@RequestBody ReligionDto dto, @PathVariable("id") Long id) {
        ReligionDto result = this.service.saveReligion(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public Page<ReligionDto> searchByPage(@RequestBody SearchDto dto) {
        Page<ReligionDto> result = this.service.searchByPage(dto);
        return result;
    }
}
