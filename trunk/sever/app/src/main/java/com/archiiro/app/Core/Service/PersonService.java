package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService extends SupportService<Person, Long> {
    List<PersonDto> getAll();

    PersonDto getPersonDto(Long id);

    PersonDto savePerson(PersonDto dto, Long id);

    Boolean deletePerson(Long id);

    Page<Person> getListPage(int pageIndex, int pageSize);
}
