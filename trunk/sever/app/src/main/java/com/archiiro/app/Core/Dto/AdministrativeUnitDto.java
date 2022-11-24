package com.archiiro.app.Core.Dto;

import com.archiiro.app.Core.Domain.AdministrativeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdministrativeUnitDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer level;
    private AdministrativeUnitDto parent;
    private List<AdministrativeUnitDto> children;

    private Long parentId;

    public AdministrativeUnitDto() {

    }

    public AdministrativeUnitDto(AdministrativeUnit entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.level = entity.getLevel();
        }
    }

    public AdministrativeUnitDto(AdministrativeUnit entity, Boolean includeChild) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.level = entity.getLevel();
            if(entity.getParent() != null) {
                this.parent = new AdministrativeUnitDto(entity.getParent(), false);
                this.parentId = entity.getParent().getId();
            }
            if(includeChild) {
                if(entity.getSubAdministrativeUnits() != null && entity.getSubAdministrativeUnits().size() > 0) {
                    List<AdministrativeUnitDto> subs = new ArrayList<AdministrativeUnitDto>();
                    for(AdministrativeUnit unit : entity.getSubAdministrativeUnits()) {
                        subs.add(new AdministrativeUnitDto(unit, false));
                    }
                    this.children = subs;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AdministrativeUnitDto getParent() {
        return parent;
    }

    public void setParent(AdministrativeUnitDto parent) {
        this.parent = parent;
    }

    public List<AdministrativeUnitDto> getChildren() {
        return children;
    }

    public void setChildren(List<AdministrativeUnitDto> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
