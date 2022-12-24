package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.ImagePath;
import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Domain.ProductSale;
import com.archiiro.app.DTShop.Dto.ImagePathDto;
import com.archiiro.app.DTShop.Dto.ProductSaleDto;
import com.archiiro.app.DTShop.Repository.ImagePathRepository;
import com.archiiro.app.DTShop.Repository.ProductRepository;
import com.archiiro.app.DTShop.Repository.ProductSaleRepository;
import com.archiiro.app.DTShop.Service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductSaleServiceImpl extends SupportServiceImpl<ProductSale, Long> implements ProductSaleService {
    @Autowired
    private ProductSaleRepository productSaleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImagePathRepository imagePathRepository;

    @Override
    public List<ProductSaleDto> getAllDto() {
        return productSaleRepository.getAll();
    }

    @Override
    public ProductSaleDto saveProductSale(ProductSaleDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        ProductSale productSale = null;
        boolean isNew = false;
        if(id != null) {
            productSale = this.findOne(id);
        }
        if(productSale == null && dto.getId() != null) {
            productSale = this.findOne(dto.getId());
        }
        if(productSale == null) {
            productSale = new ProductSale();
            isNew = true;
        }
        if(dto.getStartDate() != null) {
            productSale.setStartDate(dto.getStartDate());
        }
        if(dto.getPeriod() != null) {
            productSale.setPeriod(dto.getPeriod());
        }
        if(dto.getSale() != null) {
            productSale.setSale(dto.getSale());
        }
        if(dto.getProduct() != null && dto.getProduct().getId() != null) {
            Product product = this.productRepository.getById(dto.getProduct().getId());
            if(product != null) {
                productSale.setProduct(product);
            }
        }
        if(dto.getImagePaths() != null && dto.getImagePaths().size() > 0) {
            Iterator<ImagePathDto> iterator = dto.getImagePaths().iterator();
            HashSet<ImagePath> imagePaths = new HashSet<ImagePath>();
            while (iterator.hasNext()) {
                ImagePathDto imagePathDto = iterator.next();
                ImagePath imagePath = null;
                if(imagePathDto.getId() != null) {
                    imagePath = imagePathRepository.getImagePath(imagePathDto.getId());
                }
                if(imagePath == null) {
                    imagePath = new ImagePath();
                }
                if(productSale != null) {
                    imagePath.setProductSale(productSale);
                }
                imagePaths.add(imagePath);
            }
            if(productSale.getImagePaths() != null) {
                productSale.getImagePaths().clear();
                productSale.getImagePaths().addAll(imagePaths);
            } else {
                productSale.setImagePaths(imagePaths);
            }
        }
        productSale = this.save(productSale);
        return new ProductSaleDto(productSale, true);
    }

    @Override
    public ProductSaleDto getProductSale(Long id) {
        if(id != null) {
            ProductSale productSale = this.findOne(id);
            if(productSale != null) {
                return new ProductSaleDto(productSale, true);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteProductSale(Long id) {
        if(id != null) {
            ProductSale productSale = this.findOne(id);
            if(productSale != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public void LockSale(Date startDate, Integer period, Date currentDate) {

    }
}
