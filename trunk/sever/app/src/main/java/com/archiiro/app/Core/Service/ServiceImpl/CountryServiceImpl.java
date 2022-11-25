package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Country;
import com.archiiro.app.Core.Dto.CountryDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Repository.CountryRepository;
import com.archiiro.app.Core.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class CountryServiceImpl extends SupportServiceImpl<Country, Long> implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDto> getAllDto() {
        return countryRepository.getAllDto();
    }

    @Override
    public Boolean isExistByCode(String code) {
        if(code != null) {
            Long number = this.countryRepository.isExistByCode(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CountryDto saveCountry(CountryDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Country country = null;
        if(id != null) {
            country = this.findOne(id);
        }
        if(country == null && dto.getId() != null) {
            country = this.findOne(dto.getId());
        }
        if(country == null) {
            if(dto.getCode() != null) {
                if(!this.isExistByCode(dto.getCode())) {
                    return null;
                }
                country = new Country();
            }
        }
        if(dto.getCode() != null) {
            country.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            country.setName(dto.getName());
        }
        country.setDescription(dto.getDescription());
        country = this.save(country);
        return new CountryDto(country);
    }

    @Override
    public Boolean deleteCountry(Long id) {
        if(id != null) {
            if(this.findOne(id) != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public CountryDto getCountryDto(Long id) {
        if(id != null) {
            Country country = this.findOne(id);
            if(country != null) {
                return new CountryDto(country);
            }
        }
        return null;
    }

    @Override
    public Page<CountryDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.Core.Dto.CountryDto(entity) From Country entity ";
            String sqlCount = "Select count(entity.id) From Country entity ";
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
            Page<CountryDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
