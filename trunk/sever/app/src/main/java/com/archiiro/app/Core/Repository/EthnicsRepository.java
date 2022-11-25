package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Ethnics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicsRepository extends JpaRepository<Ethnics, Long> {
    @Query("Select count(entity.id) From Ethnics entity Where entity.code = ?1")
    Long isExistByCode(String code);
}
