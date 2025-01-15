package com.goods.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goods.product_service.model.Product;

public interface ProductRepo extends JpaRepository<Product, String> {
}