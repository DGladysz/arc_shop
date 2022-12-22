package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Domain.TypeProduct;
import com.archiiro.app.DTShop.Dto.ProductDto;
import com.archiiro.app.DTShop.Dto.SupplierDto;
import com.archiiro.app.DTShop.Repository.ProductRepository;
import com.archiiro.app.DTShop.Repository.TypeProductRepository;
import com.archiiro.app.DTShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class ProductServiceImpl extends SupportServiceImpl<Product, Long> implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeProductRepository typeProductRepository;

    @Override
    public List<ProductDto> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Boolean isExist(String code, String name) {
        if(code != null && name != null) {
            Long number = productRepository.isExistCodeOrName(code, name);
            if (number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductDto getDtoById(Long id) {
        if(id != null) {
            Product product = this.findOne(id);
            if(product != null) {
                return new ProductDto(product);
            }
        }
        return null;
    }

    @Override
    public ProductDto saveProduct(ProductDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Product product = null;
        boolean isNew = false;
        if(id != null) {
            product = this.findOne(id);
        }
        if(product == null && dto.getId() != null) {
            product = this.findOne(dto.getId());
        }
        if (product == null) {
            product = new Product();
            isNew = true;
        }
        if(dto.getCode() != null) {
            product.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            product.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if(dto.getVoided() != null) {
            product.setVoided(dto.getVoided());
        }
        if(dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if(dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }
        if(dto.getUnit() != null) {
            product.setUnit(dto.getUnit());
        }
        if(dto.getStatus() != null) {
            product.setStatus(dto.getStatus());
        }
        if(dto.getTypeProduct() != null && dto.getTypeProduct().getId() != null) {
            TypeProduct typeProduct = typeProductRepository.getTypeProduct(dto.getTypeProduct().getId());
            if(typeProduct != null) {
                product.setTypeProduct(typeProduct);
            }
        }
        product = this.save(product);
        return new ProductDto(product);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if(id != null) {
            Product product = this.findOne(id);
            if(product != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteVoided(Long id) {
        if(id != null) {
            Product product = this.findOne(id);
            if(product != null) {
                product.setVoided(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<ProductDto> searchByPage(SearchDto searchDto) {
        if(searchDto != null && searchDto.getPageIndex() != null && searchDto.getPageSize() != null) {
            int pageIndex = searchDto.getPageIndex();
            int pageSize = searchDto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.archiiro.app.DTShop.Dto.ProductDto(entity) From Product entity ";
            String sqlCount = "Select count(entity.id) From Product entity ";
            String orderBy = " Order By entity.name ";
            String whereClause = " Where (1=1) ";
            if(searchDto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, ProductDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(searchDto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + searchDto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ProductDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
