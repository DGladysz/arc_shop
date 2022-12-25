package com.archiiro.app.DTShop.Service.ServiceImpl;

import com.archiiro.app.Core.Service.ServiceImpl.SupportServiceImpl;
import com.archiiro.app.DTShop.Domain.Image;
import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Domain.ProductSale;
import com.archiiro.app.DTShop.Dto.ImageDto;
import com.archiiro.app.DTShop.Dto.ProductSaleDto;
import com.archiiro.app.DTShop.Repository.ImageRepository;
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
    private ImageRepository imageRepository;

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
        if(dto.getImages() != null && dto.getImages().size() > 0) {
            Iterator<ImageDto> iterator = dto.getImages().iterator();
            HashSet<Image> images = new HashSet<Image>();
            while (iterator.hasNext()) {
                ImageDto imageDto = iterator.next();
                Image image = null;
                if(imageDto.getId() != null) {
                    image = imageRepository.getImage(imageDto.getId());
                }
                if(image == null) {
                    image = new Image();
                }
                if(productSale != null) {
                    image.setProductSale(productSale);
                }
                images.add(image);
            }
            if(productSale.getImages() != null) {
                productSale.getImages().clear();
                productSale.getImages().addAll(images);
            } else {
                productSale.setImages(images);
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
