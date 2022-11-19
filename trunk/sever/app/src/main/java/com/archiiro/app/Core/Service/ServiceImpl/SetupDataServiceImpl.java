package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Other.Constant;
import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Dto.UserDto;
import com.archiiro.app.Core.Service.RoleService;
import com.archiiro.app.Core.Service.SetupDataService;
import com.archiiro.app.Core.Service.UserService;
import com.archiiro.app.Core.Other.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SetupDataServiceImpl implements SetupDataService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void setUpData() {
        // Create Role
        createRole();
        // Create Admin
        createAdmin();
    }
    private void createRole() {
        this.processCreateRole(Constant.ROLE_ADMIN, Constant.ROLE_ADMIN_DESCRIPTION);
        this.processCreateRole(Constant.ROLE_USER, Constant.ROLE_USER_DESCRIPTION);
    }

    private void processCreateRole(String name, String description) {
        Boolean isExist = roleService.isExistByName(name);
        if(!isExist) {
            return;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setName(name);
        roleDto.setDescription(description);
        try {
            roleService.saveRole(roleDto, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createAdmin() {
        UserDto userDto = userService.findByUsername(Constant.USER_NAME_ADMIN);
        if (Utils.isNotNull(userDto)) {
            return;
        }
        userDto = new UserDto();
        userDto.setUsername(Constant.USER_NAME_ADMIN);
        userDto.setPassword(Constant.PASSWORD);
        Role role = roleService.getRole(Constant.ROLE_ADMIN);
        if(role != null) {
            userDto.getRoles().addAll(Arrays.asList(new RoleDto(role)));
        }
        try {
            userService.saveUser(null, userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
