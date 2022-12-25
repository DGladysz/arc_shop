package com.archiiro.app.DTShop.Repository;

import com.archiiro.app.DTShop.Domain.Image;
import com.archiiro.app.DTShop.Domain.Product;
import com.archiiro.app.DTShop.Domain.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("Select entity From Image entity Where entity.id = ?1")
    Image getImage(Long id);
    @Query("Select entity.product From Image entity Where entity.product.id = ?1")
    List<Product> getImageProduct(Long productID);
    @Query("Select entity.productSale From Image entity Where entity.productSale.id = ?1")
    List<ProductSale> getImageProductSale(Long productSaleID);
}
