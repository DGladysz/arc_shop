package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;

public interface RoleService extends SupportService<Role, Long>{
    Role getRole(String name);

    Boolean isExistByName(String name);

    RoleDto saveRole(RoleDto dto, Long id);
}
