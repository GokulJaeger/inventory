package com.java.inventory.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.java.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByBrand(String brand);

	Optional<Product> findByName(String name);
	
//	List<Product> findByNameAndIsActive(String name, boolean isActive);
	
	Product findByNameAndIsActive(String name, boolean isActive);
	
	List<Product> findByIsActive(boolean active);
	
	Product findByIdAndIsActive(Integer id, boolean isActive);

}
