package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("Select new com.archiiro.app.Core.Dto.PersonDto(entity) From Person entity")
    List<PersonDto> getAll();
}
