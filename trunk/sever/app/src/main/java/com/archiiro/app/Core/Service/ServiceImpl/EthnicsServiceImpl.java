package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Ethnics;
import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Repository.EthnicsRepository;
import com.archiiro.app.Core.Service.EthnicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;

@Service
public class EthnicsServiceImpl extends SupportServiceImpl<Ethnics, Long> implements EthnicsService {
    @Autowired
    private EthnicsRepository ethnicsRepository;

    @Override
    public Boolean isExistByCode(String code) {
        if(code != null) {
            Long number = ethnicsRepository.isExistByCode(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EthnicsDto saveEthnics(EthnicsDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Ethnics ethnics = null;
        if(id != null) {
            ethnics = this.findOne(id);
        }
        if(ethnics == null && dto.getId() != null) {
            ethnics = this.findOne(dto.getId());
        }
        if(ethnics == null) {
            if(dto.getCode() != null) {
                if(!this.isExistByCode(dto.getCode())) {
                    return null;
                }
                ethnics = new Ethnics();
            }
        }
        if(dto.getCode() != null) {
            ethnics.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            ethnics.setName(dto.getName());
        }
        ethnics.setDescription(dto.getDescription());
        ethnics = this.save(ethnics);
        return new EthnicsDto(ethnics);
    }

    @Override
    public Boolean deleteEthnics(Long id) {
        if(id != null) {
            if(this.findOne(id) != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public EthnicsDto getEthnicsDto(Long id) {
        if(id != null) {
            Ethnics ethnics = this.findOne(id);
            if(ethnics != null) {
                return new EthnicsDto(ethnics);
            }
        }
        return null;
    }

    @Override
    public Page<EthnicsDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.Core.Dto.EthnicsDto(entity) From Ethnics entity ";
            String sqlCount = "Select count(entity.id) From Ethnics entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, EthnicsDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<EthnicsDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
