package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Domain.User;

import java.time.LocalDateTime;
import java.util.*;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Set<RoleDto> roles = new HashSet();
    private Long personId;
    private String displayName;
    private String email;
    private String phoneNumber;
    private String imagePath;

    public UserDto() {

    }

    public UserDto(User entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.username = entity.getUsername();
            this.password = entity.getPassword();
            if(entity.getPerson() != null && entity.getPerson().getId() != null) {
                this.personId = entity.getPerson().getId();
            }
            Iterator iterator;
            if(entity.getRoles() != null && entity.getRoles().size() > 0) {
                this.roles.clear();
                iterator = entity.getRoles().iterator();
                while (iterator.hasNext()) {
                    Role role = (Role) iterator.next();
                    this.roles.add(new RoleDto(role));
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
