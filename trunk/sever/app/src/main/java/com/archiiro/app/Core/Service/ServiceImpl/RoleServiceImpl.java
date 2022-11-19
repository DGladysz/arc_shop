package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Repository.RoleRepository;
import com.archiiro.app.Core.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl extends SupportServiceImpl<Role, Long> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(String name) {
        if(name != null) {
            return this.roleRepository.getRole(name);
        }
        return null;
    }

    @Override
    public Boolean isExistByName(String name) {
        if(name != null) {
            Long result = this.roleRepository.isExist(name);
            if(result == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RoleDto saveRole(RoleDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Role role = null;
        if(id != null) {
            role = this.findOne(id);
        }
        if(role == null && dto.getId() != null) {
            role = this.findOne(dto.getId());
        }
        if(role == null) {
            role = new Role();
        }
        if(dto.getName() != null) {
            role.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            role.setDescription(dto.getDescription());
        }
        role = this.save(role);
        return new RoleDto(role);
    }
}
