package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Dto.UserDto;
import com.archiiro.app.Core.Service.RoleService;
import com.archiiro.app.Core.Service.SetupDataService;
import com.archiiro.app.Core.Service.UserService;
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
        this.processCreateRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN_DESCRIPTION);
        this.processCreateRole(Constants.ROLE_USER, Constants.ROLE_USER_DESCRIPTION);
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
       this.processCreateUser(Constants.USER_NAME_ADMIN, Constants.PASSWORD, Constants.ROLE_ADMIN);
       this.processCreateUser(Constants.USER_NAME_USER, Constants.PASSWORD, Constants.ROLE_USER);
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
