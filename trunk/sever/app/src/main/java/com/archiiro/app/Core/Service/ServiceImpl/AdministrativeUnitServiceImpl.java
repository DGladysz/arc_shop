package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.Core.Dto.Function.AdministrativeUnitImportExcel;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Repository.AdministrativeUnitRepository;
import com.archiiro.app.Core.Service.AdministrativeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdministrativeUnitServiceImpl extends SupportServiceImpl<AdministrativeUnit, Long> implements AdministrativeUnitService {
    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepository;


    @Override
    public Boolean isExistByCode(String code) {
        if (code != null) {
            Long number = this.administrativeUnitRepository.isExistByCode(code);
            if (number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AdministrativeUnitDto getDtoById(Long id) {
        if (id != null) {
            AdministrativeUnit administrativeUnit = this.findOne(id);
            if (administrativeUnit != null) {
                return new AdministrativeUnitDto(administrativeUnit);
            }
        }
        return null;
    }

    @Override
    public List<AdministrativeUnitDto> getAllProvince() {
        return this.administrativeUnitRepository.getAllProvince();
    }

    @Override
    public List<AdministrativeUnitDto> getChildrenByParentId(Long parentId) {
        if (parentId != null) {
            List<AdministrativeUnitDto> result = new ArrayList<AdministrativeUnitDto>();
            AdministrativeUnitDto dto = administrativeUnitRepository.getDtoById(parentId);
            if (dto != null && dto.getChildren() != null && dto.getChildren().size() > 0) {
                result = dto.getChildren();
                return result;
            }
        }
        return null;
    }

    @Override
    public List<Long> getListChildrenId(Long id) {
        if (id != null) {
            String sqlSelectParent = "Select entity.id From AdministrativeUnit entity Where entity.id = :id";
            Query qSelectParent = manager.createQuery(sqlSelectParent, Long.class);
            qSelectParent.setParameter("id", id);
            List<Long> listId = qSelectParent.getResultList();
            if (listId != null) {
                String sqlSelectChildren = "Select entity.id From AdministrativeUnit entity Where entity.parent.id IS NOT NULL " +
                        " AND entity.parent.parent.id IS NOT NULL AND (entity.parent.id in :(listId) " +
                        " Or entity.parent.parent.id in (:listId))";
                String orderBy = " Oder by entity.level DESC";
                sqlSelectChildren += orderBy;
                Query q = manager.createQuery(sqlSelectChildren, Long.class);
                q.setParameter("listId", listId);
                List<Long> result = q.getResultList();
                result.addAll(listId);
                return result;
            }
        }
        return null;
    }

    @Override
    public AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto dto, Long id) {
        if (dto == null) {
            return null;
        }
        AdministrativeUnit administrativeUnit = null;
        Boolean isNew = false;
        if (id != null) {
            administrativeUnit = this.findOne(id);
        }
        if (administrativeUnit == null && dto.getId() != null) {
            administrativeUnit = this.findOne(dto.getId());
        }
        if (administrativeUnit == null) {
            if (dto.getCode() != null) {
                if (!this.isExistByCode(dto.getCode())) {
                    return null;
                }
            }
            administrativeUnit = new AdministrativeUnit();
            isNew = true;
        }
        if (dto.getCode() != null) {
            administrativeUnit.setCode(dto.getCode());
        }
        if (dto.getName() != null) {
            administrativeUnit.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            administrativeUnit.setDescription(dto.getDescription());
        }
        if (dto.getParent() != null) {
            AdministrativeUnit parent = null;
            if (dto.getParent().getId() != null) {
                parent = this.findOne(dto.getParent().getId());
            }
            if (parent != null) {
                administrativeUnit.setParent(parent);
                if (parent.getLevel() != null && parent.getLevel() > 0) {
                    administrativeUnit.setLevel(parent.getLevel() + 1);
                }
            }
        } else {
            administrativeUnit.setLevel(1); // Tỉnh
            administrativeUnit.setParent(null);
        }
        administrativeUnit = this.save(administrativeUnit);
        dto.setId(administrativeUnit.getId());
        return new AdministrativeUnitDto(administrativeUnit, false);
    }

    @Override
    public void importExcel(List<AdministrativeUnitImportExcel> dtos) {
        if(dtos != null && dtos.size() > 0) {
            List<AdministrativeUnitDto> listData = new ArrayList<AdministrativeUnitDto>();
            for(AdministrativeUnitImportExcel dto : dtos) {
                AdministrativeUnit province = null;
                AdministrativeUnit district = null;
                AdministrativeUnit commune = null;

                // Province
                if(dto.getCodeProvince() != null) {
                    List<AdministrativeUnit> listUnit = administrativeUnitRepository.getListByCode(dto.getCodeProvince());
                    if(listUnit != null && listUnit.size() > 0) {
                        province = listUnit.get(0);
                    }
                    if(province == null) {
                        province = new AdministrativeUnit();
                        province.setCode(dto.getCodeProvince());
                        if(dto.getNameProvince() != null) {
                            province.setName(dto.getNameProvince());
                        }
                        province.setLevel(Constants.PROVINCE_LEVEL);
                        province = this.save(province);
                    }
                }

                // District
                if(province != null) {
                    if(dto.getCodeDistrict() != null) {
                        List<AdministrativeUnit> listUnit = administrativeUnitRepository.getListByCode(dto.getCodeDistrict());
                        if(listUnit != null && listUnit.size() > 0) {
                            district = listUnit.get(0);
                        }
                        if(district == null) {
                            district = new AdministrativeUnit();
                            district.setParent(province);
                            district.setCode(dto.getCodeDistrict());
                            if(dto.getNameDistrict() != null) {
                                district.setName(dto.getNameDistrict());
                            }
                        }
                        district.setLevel(Constants.DISTRICT_LEVEL);
                        district = this.save(district);
                    }
                }

                // Commune
                if(district != null) {
                    if(dto.getCodeCommune() != null) {
                        List<AdministrativeUnit> listUnit = administrativeUnitRepository.getListByCode(dto.getCodeCommune());
                        if(listUnit != null && listUnit.size() > 0) {
                            commune = listUnit.get(0);
                        }
                        if(commune == null) {
                            commune = new AdministrativeUnit();
                            commune.setParent(district);
                            commune.setCode(dto.getCodeCommune());
                            if(dto.getNameCommune() != null) {
                                commune.setName(dto.getNameCommune());
                            }
                        }
                        commune.setLevel(Constants.COMMUNE_LEVEL);
                        commune = this.save(commune);
                    }
                }
            }
        }
        return;
    }

    @Override
    public Integer deleteAdministrative(Long id) {
        if (id != null) {
            List<Long> listId = this.getListChildrenId(id);
            Integer numberResult = 0;
            for (Long idItem : listId) {
                AdministrativeUnit administrativeUnit = this.findOne(idItem);
                if (administrativeUnit != null && administrativeUnit.getId() != null) {
                    this.delete(administrativeUnit.getId());
                    numberResult += 1;
                    return numberResult;
                }
            }
        }
        return null;
    }

    @Override
    public Page<AdministrativeUnitDto> searchByPage(SearchDto dto) {
        if (dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if (pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            String sql = " Select entity From AdministrativeUnit entity Left Join Fetch " +
                    " entity.subAdministrativeUnits Where entity.parent IS NULL"; // Province
            String sqlCount = "Select count(entity.id) From AdministrativeUnit entity Where entity.parent IS NULL";
            String whereClause = "";
            String oderBy = " Order by entity.name ASC";
            if (dto.getTextSearch() != null && dto.getTextSearch().length() > 0) {
                whereClause += " AND (entity.code Like :textSearch Or entity.name Like :textSearch)";
            }
            sql += whereClause + oderBy;
            Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
            Query qCount = manager.createQuery(sqlCount);
            if (dto.getTextSearch() != null && dto.getTextSearch().length() > 0) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex * pageSize);
            q.setMaxResults(pageSize);
            Long numberResult = (Long) qCount.getSingleResult();
            List<AdministrativeUnit> lst = q.getResultList();
            List<AdministrativeUnitDto> ret = new ArrayList<AdministrativeUnitDto>();
            if (lst != null && !lst.isEmpty()) {
                for (AdministrativeUnit cs : lst) {
                    ret.add(new AdministrativeUnitDto(cs));
                }
            }
            Page<AdministrativeUnitDto> page = new PageImpl<>(ret, pageable, numberResult);
            return page;
        }
        return null;
    }
}
