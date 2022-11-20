package com.archiiro.app.Core.RestController;

import com.archiiro.app.Core.Dto.PersonDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class RestPersonServiceImpl {
    @Autowired
    private PersonService personService;

    @Secured({Constants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<PersonDto>> getAll() {
        return this.personService.getAll() != null ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
