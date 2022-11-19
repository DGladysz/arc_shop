package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;

public interface RoleService {
    Role findByName(String name);

    Boolean checkRoleName(String name);

    RoleDto saveRole(RoleDto dto);
}
