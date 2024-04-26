package com.java.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
