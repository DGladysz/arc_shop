package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.AdministrativeUnit;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Repository.AdministrativeUnitRepository;
import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.Delivery;
import com.archiiro.app.DTShop.Dto.DeliveryDto;
import com.archiiro.app.DTShop.Repository.DeliveryRepository;
import com.archiiro.app.DTShop.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class DeliveryServiceImpl extends SupportServiceImpl<Delivery, Long> implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepos;

    @Override
    public List<DeliveryDto> getAll() {
        return this.deliveryRepository.getAll();
    }

    @Override
    public Boolean isExist(String code) {
        if(code != null) {
            Long number = deliveryRepository.isExist(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DeliveryDto getDtoById(Long id) {
        if(id != null) {
            Delivery delivery = this.findOne(id);
            if(delivery != null) {
                return new DeliveryDto(delivery);
            }
        }
        return null;
    }

    @Override
    public DeliveryDto saveDelivery(DeliveryDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Delivery delivery = null;
        boolean isNew = false;
        if(id != null) {
            delivery = this.findOne(id);
        }
        if(delivery == null && dto.getId() != null) {
            delivery = this.findOne(dto.getId());
        }
        if(delivery == null) {
            delivery = new Delivery();
            isNew = true;
        }
        if(dto.getCode() != null) {
            delivery.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            delivery.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            delivery.setDescription(dto.getDescription());
        }
        if(dto.getStatus() != null) {
            delivery.setStatus(dto.getStatus());
        }
        if(dto.getAddress() != null && dto.getAddress().getId() != null) {
            AdministrativeUnit address =  administrativeUnitRepos.getAdministrative(dto.getAddress().getId());
            if(address != null) {
                delivery.setAddress(address);
            }
        }
        if(dto.getAddressDetail() != null) {
            delivery.setAddressDetails(dto.getAddressDetail());
        }
        delivery = this.save(delivery);
        return new DeliveryDto(delivery);
    }

    @Override
    public Page<DeliveryDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.DTShop.Dto.DeliveryDto(entity) From Delivery entity ";
            String sqlCount = "Select count(entity.id) From Delivery entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, DeliveryDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<DeliveryDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
