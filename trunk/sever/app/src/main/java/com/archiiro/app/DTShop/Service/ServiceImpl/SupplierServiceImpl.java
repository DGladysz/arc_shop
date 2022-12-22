package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Repository.AdministrativeUnitRepository;
import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.Supplier;
import com.archiiro.app.DTShop.Dto.SupplierDto;
import com.archiiro.app.DTShop.Repository.SupplierRepository;
import com.archiiro.app.DTShop.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service  
public class SupplierServiceImpl extends SupportServiceImpl<Supplier, Long> implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepos;

    @Override
    public List<SupplierDto> getAll() {
        return this.supplierRepository.getAll();
    }

    @Override
    public Boolean isExist(String code) {
        if(code != null) {
            Long number = this.supplierRepository.isExist(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public SupplierDto getDtoById(Long id) {
        if(id != null) {
            Supplier supplier = this.supplierRepository.getById(id);
            if(supplier != null) {
                return new SupplierDto(supplier);
            }
        }
        return null;
    }

    @Override
    public SupplierDto saveSupplier(SupplierDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Supplier supplier = null;
        if(id != null) {
            supplier = this.findOne(id);
        }
        if(supplier == null && dto.getId() != null) {
            supplier = this.findOne(dto.getId());
        }
        if(supplier == null) {
            supplier = new Supplier();
        }
        if(dto.getCode() != null) {
            supplier.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            supplier .setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            supplier.setDescription(dto.getDescription());
        }
        if(dto.getStatus() != null) {
            supplier.setStatus(dto.getStatus());
        }
        if(dto.getAddress() != null && dto.getAddress().getId() != null) {
            AdministrativeUnit address = this.administrativeUnitRepos.getAdministrative(dto.getAddress().getId());
            if(address != null) {
                supplier.setAddress(address);
            }
        }
        if(dto.getAddressDetail() != null) {
            supplier.setAddressDetails(dto.getAddressDetail());
        }
        supplier = this.save(supplier);
        return new SupplierDto(supplier);
    }

    @Override
    public Boolean deleteSupplier(Long id) {
        if(id != null) {
            Supplier supplier = this.findOne(id);
            if(supplier != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<SupplierDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.DTShop.Dto.SupplierDto(entity) From Supplier entity ";
            String sqlCount = "Select count(entity.id) From Supplier entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, SupplierDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<SupplierDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
