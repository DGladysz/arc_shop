package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import com.archiiro.app.Core.Repository.PersonRepository;
import com.archiiro.app.Core.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl extends SupportServiceImpl<Person, Long> implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDto> getAll() {
        return this.personRepository.getAll();
    }

    @Override
    public PersonDto getPersonDto(Long id) {
        return null;
    }

    @Override
    public PersonDto savePerson(PersonDto dto, Long id) {
        return null;
    }

    @Override
    public Boolean deletePerson(Long id) {
        return null;
    }

    @Override
    public Page<Person> getListPage(int pageIndex, int pageSize) {
        return null;
    }
}
