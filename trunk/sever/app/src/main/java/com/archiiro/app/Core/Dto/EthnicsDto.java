package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.Ethnics;

public class EthnicsDto {
    private Long id;
    private String code;
    private String name;
    private String description;

    public EthnicsDto() {

    }

    public EthnicsDto(Ethnics entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
