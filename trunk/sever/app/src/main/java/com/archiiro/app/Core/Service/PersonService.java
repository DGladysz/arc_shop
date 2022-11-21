package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface PersonService extends SupportService<Person, Long> {
    List<PersonDto> getAll();

    PersonDto getPersonDto(Long id);

    PersonDto savePerson(PersonDto dto, Long id);

    PersonDto saveImage(Long id, MultipartFile multipartFile);

    Boolean deletePerson(Long id);

    Page<Person> getListPage(int pageIndex, int pageSize);
}
