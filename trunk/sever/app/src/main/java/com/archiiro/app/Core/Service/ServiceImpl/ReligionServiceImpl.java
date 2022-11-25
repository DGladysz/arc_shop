package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Religion;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.ReligionDto;
import com.archiiro.app.Core.Repository.ReligionRepository;
import com.archiiro.app.Core.Service.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class ReligionServiceImpl extends SupportServiceImpl<Religion, Long> implements ReligionService {
    @Autowired
    private ReligionRepository religionRepository;

    @Override
    public List<ReligionDto> getAllDto() {
        return religionRepository.getAllDto();
    }

    @Override
    public Boolean isExistByCode(String code) {
        if(code != null) {
            Long number = religionRepository.isExistByCode(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ReligionDto saveReligion(ReligionDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Religion religion = null;
        if(id != null) {
            religion = this.findOne(id);
        }
        if(religion == null && dto.getId() != null) {
            religion = this.findOne(dto.getId());
        }
        if(religion == null) {
            if(dto.getCode() != null) {
                if(!this.isExistByCode(dto.getCode())) {
                    return null;
                }
                religion = new Religion();
            }
        }
        if(dto.getCode() != null) {
            religion.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            religion.setName(dto.getName());
        }
        religion.setDescription(dto.getDescription());
        religion = this.save(religion);
        return new ReligionDto(religion);
    }

    @Override
    public Boolean deleteReligion(Long id) {
        if(id != null) {
            if(this.findOne(id) != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public ReligionDto getReligionDto(Long id) {
        if(id != null) {
            Religion religion = this.findOne(id);
            if(religion != null) {
                return new ReligionDto(religion);
            }
        }
        return null;
    }

    @Override
    public Page<ReligionDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.Core.Dto.ReligionDto(entity) From Religion entity ";
            String sqlCount = "Select count(entity.id) From Religion entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, CountryDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ReligionDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
