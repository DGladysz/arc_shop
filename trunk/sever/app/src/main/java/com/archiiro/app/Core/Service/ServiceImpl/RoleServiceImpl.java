package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Repository.RoleRepository;
import com.archiiro.app.Core.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepos;

    @Override
    public Role findByName(String name) {
        if(name != null) {
            return this.roleRepos.findByName(name);
        }
        return null;
    }

    @Override
    public Boolean checkRoleName(String name) {
        if(name != null) {
            Long result = this.roleRepos.checkRoleName(name);
            if(result == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RoleDto saveRole(RoleDto dto) {
        if(dto == null) {
            throw new IllegalArgumentException();
        } else {
            Role role = null;
            if(dto.getId() != null) {
                Optional<Role> roleOptional = this.roleRepos.findById(dto.getId());
                if(roleOptional.isPresent()) {
                    role = roleOptional.get();
                }
            }
            if(role == null) {
                role = new Role();
                if(dto.getName() != null) {
                    if(this.checkRoleName(dto.getName())) {
                        role.setName(dto.getName());
                    }
                }
            } else {
                if(this.checkRoleName(dto.getName())) {
                    role.setName(dto.getName());
                }
            }
            role = this.roleRepos.save(role);
            return role != null ? new RoleDto(role) : null;
        }
    }
}
