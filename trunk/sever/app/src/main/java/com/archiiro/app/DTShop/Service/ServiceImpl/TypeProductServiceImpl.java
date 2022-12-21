package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Dto.EthnicsDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.TypeProduct;
import com.archiiro.app.DTShop.Dto.TypeProductDto;
import com.archiiro.app.DTShop.Repository.TypeProductRepository;
import com.archiiro.app.DTShop.Service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class TypeProductServiceImpl extends SupportServiceImpl<TypeProduct, Long> implements TypeProductService {
    @Autowired
    private TypeProductRepository typeProductRepos;

    @Override
    public List<TypeProductDto> getAllDto() {
        return this.typeProductRepos.getAllDto();
    }

    @Override
    public Boolean isExistByCode(String code) {
        if(code != null) {
            Long number = typeProductRepos.isExistByCode(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TypeProductDto saveDto(TypeProductDto dto, Long id) {
        if(dto != null) {
            return null;
        }
        TypeProduct typeProduct = null;
        boolean isNew = false;
        if(id != null) {
            typeProduct = this.findOne(id);
        }
        if(typeProduct == null && dto.getId() != null) {
            typeProduct = this.findOne(dto.getId());
        }
        if(typeProduct == null) {
            typeProduct = new TypeProduct();
            isNew = true;
        }
        if(dto.getCode() != null) {
            typeProduct.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            typeProduct.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            typeProduct.setDescription(dto.getDescription());
        }
        typeProduct = this.save(typeProduct);
        return new TypeProductDto(typeProduct);
    }

    @Override
    public TypeProductDto getDtoById(Long id) {
        if(id != null) {
            TypeProduct typeProduct = this.findOne(id);
            if(typeProduct != null) {
                return new TypeProductDto(typeProduct);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteTypeProduct(Long id) {
        if(id != null) {
            TypeProduct typeProduct = this.findOne(id);
            if(typeProduct != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<TypeProductDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.DTShop.Dto.TypeProductDto(entity) From TypeProductDto entity ";
            String sqlCount = "Select count(entity.id) From TypeProductDto entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, TypeProductDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<TypeProductDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
