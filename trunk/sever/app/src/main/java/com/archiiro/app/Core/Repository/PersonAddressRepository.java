package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.PersonAddress;
import com.archiiro.app.Core.Dto.PersonAddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {
    @Query("Select new com.archiiro.app.Core.Dto.PersonAddressDto(entity) From PersonAddress entity Where entity.person.id = ?1")
    List<PersonAddressDto> getAddressByPersonId(Long id);
}
