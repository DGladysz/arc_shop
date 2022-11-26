package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Domain.PersonAddress;
import com.archiiro.app.Core.Dto.PersonAddressDto;
import com.archiiro.app.Core.Repository.PersonAddressRepository;
import com.archiiro.app.Core.Repository.PersonRepository;
import com.archiiro.app.Core.Service.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonAddressServiceImpl extends SupportServiceImpl<PersonAddress, Long> implements PersonAddressService {
    @Autowired
    private PersonAddressRepository personAddressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonAddressDto> getAddressByPersonId(Long idPerson) {
        if(idPerson != null) {
            List<PersonAddressDto> result = personAddressRepository.getAddressByPersonId(idPerson);
            return result;
        }
        return null;
    }

    @Override
    public PersonAddressDto savePersonAddress(PersonAddressDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        PersonAddress personAddress = null;
        if(id != null) {
            personAddress = this.findOne(id);
        }
        if(personAddress == null && personAddress.getId() != null) {
            personAddress = this.findOne(dto.getId());
        }
        if(personAddress == null) {
            personAddress = new PersonAddress();
        }
        if(dto.getAddress() != null) {
            personAddress.setAddress(dto.getAddress());
        }
        if(dto.getCity() != null) {
            personAddress.setCity(dto.getCity());
        }
        if(dto.getProvince() != null) {
            personAddress.setProvince(dto.getProvince());
        }
        if(dto.getCountry() != null) {
            personAddress.setCountry(dto.getCountry());
        }
        if(dto.getIdPerson() != null) {
            Person person = personRepository.getById(dto.getIdPerson());
            if(person != null) {
                dto.setIdPerson(person.getId());
            }
        }
        personAddress = this.save(personAddress);
        return new PersonAddressDto(personAddress);
    }

    @Override
    public PersonAddressDto getPersonAddress(Long id) {
        if(id != null) {
            PersonAddress personAddress = this.findOne(id);
            if(personAddress != null) {
                return new PersonAddressDto(personAddress);
            }
        }
        return null;
    }

    @Override
    public Boolean deletePersonAddress(Long id) {
        if(id != null) {
            PersonAddress personAddress = this.findOne(id);
            if(personAddress != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }
}
