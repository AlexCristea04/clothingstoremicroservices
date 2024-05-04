package com.example.products.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductIdentifier_ProductId(String productId);

    boolean existsProductByProductIdentifier_ProductId(String productId);
}
