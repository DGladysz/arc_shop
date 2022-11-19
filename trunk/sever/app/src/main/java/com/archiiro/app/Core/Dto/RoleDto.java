package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.Role;

public class RoleDto {
    private Long id;
    private String name;
    private String description;

    public RoleDto() {

    }

    public RoleDto(Role entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
