package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Other.Constant;
import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Dto.UserDto;
import com.archiiro.app.Core.Service.RoleService;
import com.archiiro.app.Core.Service.SetupDataService;
import com.archiiro.app.Core.Service.UserService;
import com.archiiro.app.Core.Other.ProcessUtils;
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
        createUser();
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
    private void createUser() {
       this.processCreateUser(Constant.USER_NAME_ADMIN, Constant.PASSWORD, Constant.ROLE_ADMIN);
       this.processCreateUser(Constant.USER_NAME_USER, Constant.PASSWORD, Constant.ROLE_USER);
    }

    private void processCreateUser(String username, String password, String role) {
        Boolean isExist = userService.isExist(username);
        if(!isExist) {
            return;
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        Role getRole = roleService.getRole(role);
        if(getRole != null) {
            userDto.getRoles().addAll(Arrays.asList(new RoleDto(getRole)));
        }
        try {
            userService.saveUser(userDto, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
