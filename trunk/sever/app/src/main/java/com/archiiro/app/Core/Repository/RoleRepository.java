package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("Select count(role.id) From Role role Where role.name = ?1")
    Long checkRoleName(String name);

    @Query("Select role From Role role Where role.name = ?1")
    Role findByName(String name);
}
