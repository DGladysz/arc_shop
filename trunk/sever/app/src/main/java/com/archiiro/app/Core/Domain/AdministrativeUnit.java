package com.archiiro.app.Core.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "tbl_administrative_unit")
@XmlRootElement
public class AdministrativeUnit extends BaseObject{
    @Column(name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AdministrativeUnit parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<AdministrativeUnit> subAdministrativeUnits;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AdministrativeUnit getParent() {
        return parent;
    }

    public void setParent(AdministrativeUnit parent) {
        this.parent = parent;
    }

    public Set<AdministrativeUnit> getSubAdministrativeUnits() {
        return subAdministrativeUnits;
    }

    public void setSubAdministrativeUnits(Set<AdministrativeUnit> subAdministrativeUnits) {
        this.subAdministrativeUnits = subAdministrativeUnits;
    }
}
