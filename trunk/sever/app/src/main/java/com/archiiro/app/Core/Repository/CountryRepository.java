package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("Select count(entity.id) From Country entity Where entity.code = ?1")
    Long isExistByCode(String code);
}
