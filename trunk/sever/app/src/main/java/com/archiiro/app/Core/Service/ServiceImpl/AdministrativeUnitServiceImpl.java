package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.AdministrativeUnitDto;
import com.archiiro.app.Core.Dto.SearchDto;
import com.archiiro.app.Core.Repository.AdministrativeUnitRepository;
import com.archiiro.app.Core.Service.AdministrativeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class AdministrativeUnitServiceImpl extends SupportServiceImpl<AdministrativeUnit, Long> implements AdministrativeUnitService {
    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepository;


    @Override
    public Boolean isExistByCode(String code) {
        if(code != null) {
            Long number = this.administrativeUnitRepository.isExistByCode(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AdministrativeUnitDto getDtoById(Long id) {
        if(id != null) {
            AdministrativeUnit administrativeUnit = this.findOne(id);
            if(administrativeUnit != null) {
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
    public List<AdministrativeUnitDto> getListWard(Long administrativeId) {
        if(administrativeId != null) {
            String sql = "Select new com.archiiro.app.Core.Dto.AdministrativeUnitDto(entity, true) " +
                         " From AdministrativeUnit entity Where (1=1) And entity.parent.parent is NOT NULL";
            String whereClause = "";
            String orderBy = "Order By entity.parent.parent.name asc";
            if(administrativeId != null) {
                whereClause += " And (entity.parent.id = :Id Or entity.parent.parent.id = :Id";
            }
            sql += whereClause + orderBy;
            Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
            if (administrativeId != null) {
                q.setParameter("Id", administrativeId);
            }
            List<AdministrativeUnitDto> result = q.getResultList();
            if(result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public List<AdministrativeUnitDto> getAllByParentId(Long parentId) {
        return null;
    }

    @Override
    public List<Long> getListId(Long id) {
        return null;
    }

    @Override
    public AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto dto, Long id) {
        return null;
    }

    @Override
    public Integer deleteAdministrative(Long id) {
        return null;
    }

    @Override
    public Page<AdministrativeUnitDto> searchByPage(SearchDto dto) {
        return null;
    }
}
