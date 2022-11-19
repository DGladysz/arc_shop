package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.User;
import com.archiiro.app.Core.Dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select new com.archiiro.app.Core.Dto.UserDto(entity) From User entity")
    List<UserDto> getAll();
    @Query("Select new com.archiiro.app.Core.Dto.UserDto(entity) From User entity Where entity.username = ?1")
    UserDto getUserDto(String username);

    @Query("Select entity From User entity Where entity.username = ?1")
    User getUser(String username);

    @Query("Select count(entity.id) From User entity Where entity.username = ?1")
    Long isExist(String username);
}
