package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {
    @Query("Select count(entity.id) From Religion entity Where entity.code = ?1")
    Long isExistByCode(String code);
}
