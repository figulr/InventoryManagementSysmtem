package com.fenikskrylo.dechallintier.feniksystem.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p ORDER BY p.id DESC")
    List<Products> findAllDesc();

    Optional<Products> findByBarcodeId(long barcodeId);

    //    @Query("SELECT p FROM Products p WHERE p.productName like ?1")
    Optional<List<Products>> findByBarcodeIdContaining(long barcodeId);

    Optional<List<Products>> findByProductNameContaining(String productName);

    Optional<List<Products>> findByBrandContaining(String brand);
}
