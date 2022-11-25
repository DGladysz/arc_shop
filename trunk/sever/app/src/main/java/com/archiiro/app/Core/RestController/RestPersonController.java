package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "", allowedHeaders = "")
public class RestPersonController {
    @Autowired
    private PersonService personService;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<PersonDto> getAll() {
        List<PersonDto> result = this.personService.getAll();
        return result;
    }@Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto dto) {
        PersonDto result = this.personService.savePerson(dto, null);
        return new ResponseEntity<PersonDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto dto, @PathVariable("id") Long id) {
        PersonDto result = this.personService.savePerson(dto, id);
        return new ResponseEntity<PersonDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/image/{id}", method = RequestMethod.POST)
    public PersonDto uploadImage(@PathVariable("id") Long id, @RequestParam("upload-image") MultipartFile multipartFile) {
        PersonDto result = this.personService.saveImage(id, multipartFile);
        return result;
    }

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
    public Page<Person> getListPage(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        Page<Person> result = this.personService.getListPage(pageIndex, pageSize);
        return result;
    }
}
