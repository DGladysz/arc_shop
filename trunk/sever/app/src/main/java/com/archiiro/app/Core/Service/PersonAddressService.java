package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.PersonAddress;
import com.archiiro.app.Core.Dto.PersonAddressDto;

import java.util.List;

public interface PersonAddressService extends SupportService<PersonAddress, Long>{
    List<PersonAddressDto> getAddressByPersonId(Long idPerson);

    PersonAddressDto savePersonAddress(PersonAddressDto dto, Long id);

    PersonAddressDto getPersonAddress(Long id);

    Boolean deletePersonAddress (Long id);

}
